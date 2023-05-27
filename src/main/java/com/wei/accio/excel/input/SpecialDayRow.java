package com.wei.accio.excel.input;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wei.accio.excel.convert.BooleanConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class SpecialDayRow {

    @ExcelProperty("日期")
    private LocalDate date;

    @ExcelProperty(value = "是否上班", converter = BooleanConverter.class)
    private Boolean isWork;

}
