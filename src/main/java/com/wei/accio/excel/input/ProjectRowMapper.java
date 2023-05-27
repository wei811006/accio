package com.wei.accio.excel.input;

import com.wei.accio.domain.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectRowMapper {

    Project convert(ProjectRow row);

    List<Project> convert(List<ProjectRow> list);
}
