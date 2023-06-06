package com.wei.accio.domain;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;

public class Employees {

    @Getter(AccessLevel.PACKAGE)
    private List<Employee> employees;

    Employees(List<Employee> employees) {
        this.employees = employees;
    }
}
