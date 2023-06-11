package com.wei.accio.excel.input;

import com.wei.accio.domain.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjectRowMapper {

    @Mappings({
            @Mapping(target="mondayShift", expression = "java(mapToShifts(row.getMondayShift()))"),
            @Mapping(target="tuesdayShift", expression = "java(mapToShifts(row.getTuesdayShift()))"),
            @Mapping(target="wednesdayShift", expression = "java(mapToShifts(row.getWednesdayShift()))"),
            @Mapping(target="thursdayShift", expression = "java(mapToShifts(row.getThursdayShift()))"),
            @Mapping(target="fridayShift", expression = "java(mapToShifts(row.getFridayShift()))"),
            @Mapping(target="saturdayShift", expression = "java(mapToShifts(row.getSaturdayShift()))"),
            @Mapping(target="sundayShift", expression = "java(mapToShifts(row.getSundayShift()))"),
    })
    Project convert(ProjectRow row);

    List<Project> convert(List<ProjectRow> list);

    default List<String> mapToShifts(String shift) {
        if (StringUtils.isEmpty(shift)) return new ArrayList<>();

        String[] shiftStrings = shift.split("");
        return Arrays.stream(shiftStrings)
                .collect(Collectors.toList());
    }
}
