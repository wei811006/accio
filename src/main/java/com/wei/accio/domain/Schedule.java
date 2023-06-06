package com.wei.accio.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class Schedule {

    private Employees employees;

    private Projects projects;

    private SpecialDays specialDays;

    public Schedule() {}

    public void process(LocalDate month) {
        log.info("Start creating a schedule");

        LocalDate today = resetToDay1(month);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth()); // 取得本月的最後一天

        while (!today.isAfter(endOfMonth)) {
            // 在此處處理每一天的邏輯

            // 當天是否為上班日
            boolean isWorkDay = specialDays.isWork(today);
            log.info("Day: " + today + ", isWorkDay: " + isWorkDay);

            // 取得當日專案需求人力

            // 處理每位員工
            Iterator<Employee> iterator = employees.getEmployees().iterator();
            while (iterator.hasNext()) {
                Employee targetEmployee = iterator.next();
                log.info(targetEmployee.getName());

                // 判斷員工是否休假


            }

            today = today.plusDays(1); // 移至下一天
        }

    }

    public void setSpecialDays(List<SpecialDay> specialDays) {
        this.specialDays = new SpecialDays(specialDays);
    }

    public void setProjects(List<Project> projects) {
        this.projects = new Projects(projects);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = new Employees(employees);
    }

    public List<Employee> getEmployees() {
        return this.employees.getEmployees();
    }

    private LocalDate resetToDay1(LocalDate month) {
        return month.withDayOfMonth(1);
    }

}
