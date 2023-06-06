package com.wei.accio.excel.output;

import com.alibaba.excel.metadata.data.FormulaData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.wei.accio.domain.Employee;
import com.wei.accio.excel.utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBody {

    public static List<List<WriteCellData<String>>> body(List<Employee> employees) {

        List<List<WriteCellData<String>>> body = new ArrayList<>();

        int row = 3;

        for (Employee employee: employees) {
            List<WriteCellData<String>> schedule = new ArrayList<>();

            // Name
            schedule.add(ExcelUtils.transToCellData("\"" + employee.getName() + "\"") );
            for(int i = 0; i<31; i++) {
                schedule.add(ExcelUtils.transToCellData(String.valueOf(i)));
            }

            // 休假天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"休\")"));
            // 工作天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"<>休\")"));
            // A班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-A\")"));
            // B班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-B\")"));
            // C班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-C\")"));
            // D班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-D\")"));
            // E班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-E\")"));
            // F班天數
            schedule.add(ExcelUtils.transToCellData("COUNTIFS(B" + row + ":" + ExcelUtils.convertToExcelColumn(30) + row + ",\"-F\")"));

            body.add(schedule);
            row++;
        }

        return body;
    }




}
