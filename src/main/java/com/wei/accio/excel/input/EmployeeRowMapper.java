package com.wei.accio.excel.input;

import com.wei.accio.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeRowMapper {

    @Mappings({
            @Mapping(target = "dayOff", expression = "java(mapToDateList(row.getDayOff()))"),
            @Mapping(target = "mustDayOff", expression = "java(mapToDateList(row.getMustDayOff()))"),
    })
    Employee convert(EmployeeRow row);

    LinkedList<Employee> convert(List<EmployeeRow> rows);

    default List<LocalDate> mapToDateList(String dayOff) {
        if (StringUtils.isEmpty(dayOff)) return new ArrayList<>();

        String[] dateStrings = dayOff.split("、");
        return Arrays.stream(dateStrings)
                .map(LocalDate::parse)
                .collect(Collectors.toList());
    }
}
