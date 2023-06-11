package com.wei.accio.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

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

        // 在此處處理每一天的邏輯
        while (!today.isAfter(endOfMonth)) {

            // 當天是否為上班日
            boolean isWorkDay = specialDays.isWork(today);
            log.info("Day: " + today + ", isWorkDay: " + isWorkDay);

            // 取得當日專案人力需求
            Queue<String> projectRequirements = projects.getProjectRequirements(today, isWorkDay);

            // 取得專案所需數量
            int requiredCount = projects.requiredCount(today, isWorkDay);

            // 取得當日可分配員工
            List<Employee> availableEmployees = employees.getAvailableEmployees(today, requiredCount);

            // 開始分配
            while(!availableEmployees.isEmpty()) {

                // 依照順序取得專案需求
                String projectRequirement = projectRequirements.poll();

                // 判斷員工是否符合專案需求
                Iterator<Employee> iterator = availableEmployees.iterator();
                while (iterator.hasNext()) {
                    Employee employee = iterator.next();
                    if (employee.possesses(projectRequirement.substring(0, projectRequirement.indexOf("-")))) {
                        log.info(employee.getName() + " has been assigned to " + projectRequirement);
                        employee.assignWork(projectRequirement);
                        iterator.remove();
                        break;
                    }
                }

                // 將需求放回順序的最後一個
                projectRequirements.offer(projectRequirement);
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
