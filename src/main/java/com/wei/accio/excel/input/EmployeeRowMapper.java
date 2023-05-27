package com.wei.accio.excel.input;

import com.wei.accio.domain.Employee;
import org.mapstruct.Mapper;

import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeRowMapper {

    Employee convert(EmployeeRow row);

    LinkedList<Employee> convert(List<EmployeeRow> rows);

}
