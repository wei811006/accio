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

    // Fixed employee holiday schedule
    private String fixedDayOff;

    // Fixed work schedule of employee
    private String fixedSchedule;

    // The number of consecutive working days for the employee
    private Integer continuously;

    private Integer workerDayCount = 0;

    private List<LocalDate> dayOff;

    private List<LocalDate> mustDayOff;

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
            assignDayOff(DayOff.dayOff);
            return true;
        }

        // 必休
        if (mustDayOff.contains(date)) {
            assignDayOff(DayOff.must);
            return true;
        }

        // 避免連續上班
        if (continuously >= 5) {
            assignDayOff(DayOff.must);
            return true;
        }
        return false;
    }

    void assignWork(String shift) {
        continuously++;
        workerDayCount++;
        shifts.add(shift);
    }

    void assignDayOff(DayOff dayOff) {
        continuously = 0;
        shifts.add(dayOff.getDisplayName());
    }

}
