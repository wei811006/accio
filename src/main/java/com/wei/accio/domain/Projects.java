package com.wei.accio.domain;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Projects {

    @Getter(value = AccessLevel.PACKAGE)
    private List<Project> projects;

    Projects(List<Project> projects) {
        this.projects = projects;
    }

    Queue<String> getProjectRequirements(LocalDate date, boolean isWorkDay) {
        Queue<String> requirements = new LinkedList<>();

        for(Project project: projects) {
            for(String shift: project.getShifts(isWorkDay ? date : LocalDate.now().with(DayOfWeek.SUNDAY))) {
                requirements.add(project.getName() + "-" + shift);
            }
        }
        return requirements;
    }

    int requiredCount(LocalDate date, boolean isWorkDay) {
        if (!isWorkDay || date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
            return getProjectRequirements(date, isWorkDay).size();
        }
        return -1;
    }
}
