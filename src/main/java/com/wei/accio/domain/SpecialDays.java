package com.wei.accio.domain;

import java.time.LocalDate;
import java.util.List;

public class SpecialDays {

    private List<SpecialDay> specialDays;

    SpecialDays(List<SpecialDay> specialDays) {
        this.specialDays = specialDays;
    }

    /**
     *
     * @param targetDay
     * @return false為假日, true為上班日
     */
    boolean isWork(LocalDate targetDay) {
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
