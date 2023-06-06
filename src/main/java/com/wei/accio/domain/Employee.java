package com.wei.accio.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Employee {

    private String employeeID;

    private String name;

    private String project;

    private String level;

    // Fixed employee holiday schedule
    private String fixedDayOff;

    // Fixed work schedule of employee
    private String fixedSchedule;

    // The number of consecutive working days for the employee
    private Integer continuously;

    private Integer vacationDaysCount = 0;

    private Integer workDaysCount = 0;

}
