package com.wei.accio.excel.input;

import com.wei.accio.domain.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProjectRowMapper {

    @Mapping(target="employeeId", source="entity.id")
    Project rowToData(ProjectRow row);

}
