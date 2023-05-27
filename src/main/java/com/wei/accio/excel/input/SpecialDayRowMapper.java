package com.wei.accio.excel.input;

import com.wei.accio.domain.SpecialDay;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialDayRowMapper {

    SpecialDay convert(SpecialDayRow row);

    List<SpecialDay> convert(List<SpecialDayRow> rows);

}
