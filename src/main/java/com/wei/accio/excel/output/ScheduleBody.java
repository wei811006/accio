package com.wei.accio.excel.output;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.wei.accio.domain.Employee;
import com.wei.accio.excel.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBody {

    public static List<List<WriteCellData<String>>> body(List<Employee> employees) {

        List<List<WriteCellData<String>>> body = new ArrayList<>();

        updateEmployeeSchedule(body, employees);

        return body;
    }

    public static WriteCellStyle style() {
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        //字體大小
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short)12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return contentWriteCellStyle;
    }

    private static void updateEmployeeSchedule(List<List<WriteCellData<String>>> body, List<Employee> employees) {
        int row = 3;

        for (Employee employee: employees) {
            List<WriteCellData<String>> schedule = new ArrayList<>();

            // Name
            schedule.add(ExcelUtils.transToCellData("\"" + employee.getName() + "\"") );

            for(String shift: employee.getShifts()) {
                schedule.add(ExcelUtils.transToCellData("\"" + shift + "\""));
            }

            int days = employee.getShifts().size();

            // 休假天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*休*\")"));
            // 工作天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"<>*休*\")"));
            // A班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-A\")"));
            // B班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-B\")"));
            // C班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-C\")"));
            // D班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-D\")"));
            // E班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-E\")"));
            // F班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(days) + row + ",\"*-F\")"));

            body.add(schedule);
            row++;
        }
    }

}
