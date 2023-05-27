package com.wei.accio;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wei.accio.domain.Schedule;
import com.wei.accio.excel.input.*;
import com.wei.accio.excel.output.ScheduleBody;
import com.wei.accio.excel.output.ScheduleHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
public class AccioService {

    public void schedule(MultipartFile file, LocalDate month, OutputStream outputStream) throws Exception {

        // Read Excel Data
        ProjectRowListener projectRowListener = new ProjectRowListener();
        EmployeeRowListener employeeRowListener = new EmployeeRowListener();
        SpecialDayRowListener specialDayRowListener = new SpecialDayRowListener();

        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {

            // Project
            ReadSheet projectSheet =
                    EasyExcel.readSheet("專案").head(ProjectRow.class).registerReadListener(projectRowListener).build();

            // Employee
            ReadSheet employeeSheet =
                    EasyExcel.readSheet("員工").head(EmployeeRow.class).registerReadListener(employeeRowListener).build();

            // Special Day
            ReadSheet specialDaySheet =
                    EasyExcel.readSheet("特別日").head(SpecialDayRow.class).registerReadListener(specialDayRowListener).build();

            // Read From Excel
            excelReader.read(projectSheet, employeeSheet, specialDaySheet);
        }

        // Init a staffing plan
        ProjectRowMapper projectRowMapper = new ProjectRowMapperImpl();
        EmployeeRowMapper employeeRowMapper = new EmployeeRowMapperImpl();
        SpecialDayRowMapper specialDayRowMapper = new SpecialDayRowMapperImpl();
        Schedule schedule = new Schedule(
                employeeRowMapper.convert(employeeRowListener.getEmployees()),
                projectRowMapper.convert(projectRowListener.getProjects()),
                specialDayRowMapper.convert(specialDayRowListener.getDays())
        );

        // Do staffing plan
        schedule.process(month);

        // Export Excel
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        EasyExcel.write(outputStream)
                .sheet(sdf.format(new Date()))
                .head(ScheduleHeader.header(LocalDate.now()))
                .doWrite(ScheduleBody.body(schedule.getEmployees()));
    }



}
