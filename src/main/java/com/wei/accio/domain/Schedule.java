package com.wei.accio.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class Schedule {

    @Getter
    private List<Employee> employees;

    private List<Project> projects;

    private List<SpecialDay> specialDays;

    public Schedule(LinkedList<Employee> employees, List<Project> projects, List<SpecialDay> specialDays) {
        this.employees = employees;
        this.projects = projects;
        this.specialDays = specialDays;
    }

    public void process(LocalDate month) {
        log.info("Start creating a schedule");

        LocalDate today = resetToDay1(month);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth()); // 取得本月的最後一天

        while (!today.isAfter(endOfMonth)) {
            // 在此處處理每一天的邏輯
            boolean isWorkDay = isWork(today);

            log.info("Day: " + today + ", isWorkDay: " + isWorkDay);

            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                Employee targetEmployee = iterator.next();

                log.info(targetEmployee.getName());
            }

            today = today.plusDays(1); // 移至下一天
        }

    }

    private LocalDate resetToDay1(LocalDate month) {
        return month.withDayOfMonth(1);
    }

    /**
     *
     * @param targetDay
     * @return false為假日, true為上班日
     */
    private boolean isWork(LocalDate targetDay) {
        // 判斷是否為特殊連假或是補班日
        for (SpecialDay day: specialDays) {
            if (targetDay.isEqual(day.getDate())) {
                return day.getIsWork();
            }
        }

        // 判斷是否為週六、週日
        int dayOfWeek = targetDay.getDayOfWeek().getValue();
        if (dayOfWeek == 6 | dayOfWeek == 7) {
            return false;
        }

        return true;
    }

}
