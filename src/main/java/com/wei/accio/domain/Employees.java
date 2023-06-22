package com.wei.accio.domain;

import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Employees {

    @Getter(AccessLevel.PACKAGE)
    private List<Employee> employees;

    Employees(List<Employee> employees) {
        this.employees = employees;
    }

    List<Employee> getAvailableEmployeesLevel1(LocalDate date, int count) {

        List<Employee> availableEmployees = new LinkedList<>();
        List<Employee> currEmployees = employees.stream().filter(employee -> employee.getLevel().equals("一")).collect(Collectors.toList());

        // 依照工作日數量排序
        if (count != -1) {
            Comparator<Employee> comparator =
                    Comparator.comparingInt(Employee::getWorkerDayCount)
                            .thenComparing(Employee::getContinuousWork);
            Collections.sort(currEmployees, comparator);
        }

        for(Employee employee: currEmployees) {
            // 六日或是特殊日只需要一定人數就夠
            if (count != -1 && availableEmployees.size() >= count) {
                employee.assignDayOffRotate();
                continue;
            }

            // 這個人當天是否上班
            if (employee.work(date)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
}
