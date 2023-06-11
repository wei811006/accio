package com.wei.accio.domain;

import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

public class Employees {

    @Getter(AccessLevel.PACKAGE)
    private List<Employee> employees;

    Employees(List<Employee> employees) {
        this.employees = employees;
    }

    List<Employee> getAvailableEmployees(LocalDate date, int count) {

        List<Employee> availableEmployees = new LinkedList<>();
        List<Employee> currEmployees = new ArrayList<>(employees);

        if (count != -1) {
            Comparator<Employee> comparator =
                    Comparator.comparingInt(Employee::getWorkerDayCount)
                            .thenComparing(Employee::getContinuously);
            Collections.sort(currEmployees, comparator);
        }

        for(Employee employee: currEmployees) {
            // 六日或是特殊日只需要一定人數就夠
            if (count != -1 && availableEmployees.size() >= count) {
                employee.assignDayOff(DayOff.rotate);
                continue;
            }

            if (employee.work(date)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
}
