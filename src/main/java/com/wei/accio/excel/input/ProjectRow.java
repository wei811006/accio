package com.wei.accio.excel.input;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProjectRow {

    @ExcelProperty("名稱")
    private String name;

    @ExcelProperty("週一")
    private String mondayShift;

    @ExcelProperty("週二")
    private String tuesdayShift;

    @ExcelProperty("週三")
    private String wednesdayShift;

    @ExcelProperty("週四")
    private String thursdayShift;

    @ExcelProperty("週五")
    private String fridayShift;

    @ExcelProperty("週六")
    private String saturdayShift;

    @ExcelProperty("週日")
    private String sundayShift;

}

