package com.wei.accio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Employee {

    private String employeeID;

    private String name;

    private String projectSkill;

    private String level;

    // 固定休假
    private String fixedDayOff;

    // 班別
    private String fixedSchedule;

    // 連續工作日數量
    private Integer continuousWork;

    // 連續休假日數量
    private Integer continuousDayOff = 0;

    // 當月總工作日數量
    private Integer workerDayCount = 0;

    // 特休
    private List<LocalDate> dayOff;

    // 必休
    private List<LocalDate> mustDayOff;

    // 值班
    private List<String> shifts = new ArrayList<>();

    /**
     * 這個人當天是否上班
     *
     * @param date
     * @return
     */
    boolean work(LocalDate date) {
        if (isDayOff(date)) {
            return false;
        }
        return true;
    }

    /**
     * 判斷這個人會不會這個專案
     *
     * @param project
     * @return
     */
    boolean possesses(String project) {
        return projectSkill.contains(project);
    }

    private boolean isDayOff(LocalDate date) {
        // 特休
        if (dayOff.contains(date)) {
            assignDayOff();
            return true;
        }

        // 必休
        if (mustDayOff.contains(date)) {
            assignDayOffMust();
            return true;
        }

        // 避免連續上班
        if (continuousWork >= 5) {
            continuousDayOff = 2;
            assignDayOffMust();
            return true;
        }

        // 需要連續休假二日
        if (continuousDayOff > 0) {
            assignDayOffRotate();
            return true;
        }

        return false;
    }

    void assignWork(String shift) {
        continuousWork++;
        workerDayCount++;
        shifts.add(shift);
    }

    void assignDayOffMust() {
        assignDayOff(DayOff.must);
    }

    void assignDayOffRotate() {
        assignDayOff(DayOff.rotate);
    }

    void assignDayOff() {
        assignDayOff(DayOff.dayOff);
    }

    void assignDayOff(DayOff dayOff) {
        continuousWork = 0;
        continuousDayOff--;
        shifts.add(dayOff.getDisplayName());
    }

}
