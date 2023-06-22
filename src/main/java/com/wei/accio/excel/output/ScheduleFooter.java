package com.wei.accio.excel.output;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.wei.accio.domain.Employee;
import com.wei.accio.domain.Project;
import com.wei.accio.domain.Projects;
import com.wei.accio.excel.utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;

import static com.wei.accio.excel.utils.ExcelUtils.convertToExcelColumn;

public class ScheduleFooter {

    public static List<List<WriteCellData<String>>> footer(List<Project> projects, int employeeCount, int dayCount) {

        List<List<WriteCellData<String>>> footer = new ArrayList<>();

        projectCount(footer, projects, employeeCount, dayCount);

        dayOffCount(footer, employeeCount, dayCount);

        return footer;
    }

    public static void projectCount(List<List<WriteCellData<String>>> footer, List<Project> projects, int employeeCount, int dayCount) {

        int totalRow = employeeCount + 2;

        for (Project project: projects) {
            List<WriteCellData<String>> schedule = new ArrayList<>();

            // Name
            schedule.add(ExcelUtils.transToCellData("\"" + project.getName() + "人數\"") );

            for(int i = 0; i < dayCount; i++) {
                schedule.add(
                        ExcelUtils.transToCellData(
                                "COUNTIFS(" + convertToExcelColumn(i+2) +"3:" + convertToExcelColumn(i+2) + totalRow +",\"*" + project.getName() + "*\")"));
            }

            footer.add(schedule);
        }
    }

    public static void dayOffCount(List<List<WriteCellData<String>>> footer, int employeeCount, int dayCount) {
        int totalRow = employeeCount + 2;

        List<WriteCellData<String>> schedule = new ArrayList<>();

        // Name
        schedule.add(ExcelUtils.transToCellData("\"休假人數\"") );

        for(int i = 0; i < dayCount; i++) {
            schedule.add(
                    ExcelUtils.transToCellData(
                            "COUNTIFS(" + convertToExcelColumn(i + 2) + "3:" + convertToExcelColumn(i + 2) + totalRow + ",\"*休*\")"));
        }

        footer.add(schedule);
    }
}
