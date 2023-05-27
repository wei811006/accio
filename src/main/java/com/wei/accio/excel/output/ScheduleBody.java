package com.wei.accio.excel.output;

import com.wei.accio.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBody {

    public static List<List<String>> body(List<Employee> employees) {

        List<List<String>> body = new ArrayList<>();

        for (Employee employee: employees) {
            List<String> schedule = new ArrayList<>();

            // Name
            schedule.add(employee.getName());
            for(int i = 0; i<30; i++) {
                schedule.add(String.valueOf(i));
            }

            body.add(schedule);
        }

        return body;
    }

}
