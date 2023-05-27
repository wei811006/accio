package com.wei.accio.excel.output;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScheduleHeader {

    public static List<List<String>> header(LocalDate targetMonth) {
        List<List<String>> header = new ArrayList<>();
        header.add(List.of("","組內成員"));

        LocalDate firstDayOfMonth = targetMonth.withDayOfMonth(1);
        LocalDate lastDayOfMonth = targetMonth.withDayOfMonth(targetMonth.lengthOfMonth());

        for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
            int dayOfMonth = date.getDayOfMonth();
            int monthValue = date.getMonthValue();
            String dayOfWeek  = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.TAIWAN);

            List<String> day = new ArrayList<>();
            day.add(monthValue + "/" + dayOfMonth);
            day.add("" + dayOfWeek);

            header.add(day);
        }

        header.add(List.of("", "休假天數"));
        header.add(List.of("", "工作天數"));
        header.add(List.of("", "A班"));
        header.add(List.of("", "B班"));
        header.add(List.of("", "C班"));
        header.add(List.of("", "D班"));
        header.add(List.of("", "E班"));
        header.add(List.of("", "F班"));

        return header;
    }
}
