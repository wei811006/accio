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
    private String monday;

    @ExcelProperty("週二")
    private String tuesday;

    @ExcelProperty("週三")
    private String wednesday;

    @ExcelProperty("週四")
    private String thursday;

    @ExcelProperty("週五")
    private String friday;

    @ExcelProperty("週六")
    private String saturday;

    @ExcelProperty("週日")
    private String sunday;

}

