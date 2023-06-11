package com.wei.accio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Project {

    @Getter
    private String name;

    private List<String> mondayShift;

    private List<String> tuesdayShift;

    private List<String> wednesdayShift;

    private List<String> thursdayShift;

    private List<String> fridayShift;

    private List<String> saturdayShift;

    private List<String> sundayShift;

    List<String> getShifts(LocalDate date) {
        switch (date.getDayOfWeek().getValue()) {
            case 1:
                return mondayShift;
            case 2:
                return tuesdayShift;
            case 3:
                return wednesdayShift;
            case 4:
                return thursdayShift;
            case 5:
                return fridayShift;
            case 6:
                return saturdayShift;
            case 7:
                return sundayShift;
            default:
                return null;
        }
    }
}
