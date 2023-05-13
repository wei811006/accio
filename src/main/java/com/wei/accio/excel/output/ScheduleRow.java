package com.wei.accio.excel.output;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ScheduleRow {

    @ExcelProperty("工號")
    private String employeeId;

    @ExcelProperty("姓名")
    private String name;

}
