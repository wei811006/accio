package com.wei.accio;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wei.accio.domain.Schedule;
import com.wei.accio.excel.input.EmployeeRow;
import com.wei.accio.excel.input.EmployeeRowListener;
import com.wei.accio.excel.input.ProjectRow;
import com.wei.accio.excel.input.ProjectRowListener;
import com.wei.accio.excel.output.ScheduleHeader;
import com.wei.accio.excel.output.ScheduleRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AccioService {

    public void schedule(MultipartFile file, String month, OutputStream outputStream) throws Exception {

        // Read Excel Data
        ProjectRowListener projectRowListener = new ProjectRowListener();
        EmployeeRowListener employeeRowListener = new EmployeeRowListener();
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {

            // Project
            ReadSheet projectSheet =
                    EasyExcel.readSheet("專案").head(ProjectRow.class).registerReadListener(projectRowListener).build();

            // Employee
            ReadSheet employeeSheet =
                    EasyExcel.readSheet("員工").head(EmployeeRow.class).registerReadListener(employeeRowListener).build();

            // Special Day

            // Read From Excel
            excelReader.read(projectSheet, employeeSheet);
        }

        // Generate a staffing plan
        Schedule schedule = new Schedule();


        // Export result to Excel
        List<ScheduleRow> result = new ArrayList<>();
        result.add(new ScheduleRow("1111", "TEST1"));
        result.add(new ScheduleRow("2222", "TEST2"));
//        ExcelWriter writer = EasyExcel.write(outputStream, ScheduleRow.class).build();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        EasyExcel.write(outputStream, ScheduleRow.class)
                .sheet(sdf.format(new Date()))
                .head(ScheduleHeader.header(LocalDate.now()))
                .doWrite(result);
    }

}
