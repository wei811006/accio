package com.wei.accio.excel.input;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeRow {

    @ExcelProperty("工號")
    private String employeeID;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("分配專案")
    private String projectSkill;

    @ExcelProperty("一二線")
    private String level;

    @ExcelProperty("固定休假")
    private String fixedDayOff;

    @ExcelProperty("固定班別")
    private String fixedSchedule;

    @ExcelProperty("已經連續上班")
    private String continuousWork;

    @ExcelProperty("休假")
    private String dayOff;

    @ExcelProperty("必假")
    private String mustDayOff;
}
