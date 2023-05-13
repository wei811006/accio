package com.wei.accio.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
public class Employee {

    private String employeeID;

    private String name;

    private String level;

    private Integer vacationDays = 0;

    private Integer workDays = 0;

    private Integer continuousWorkingDays = 0;


}
