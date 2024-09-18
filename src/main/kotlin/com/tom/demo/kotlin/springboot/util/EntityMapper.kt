package com.tom.demo.kotlin.springboot.util

import com.tom.demo.kotlin.springboot.dto.EmployeeDTO
import com.tom.demo.kotlin.springboot.model.Employee
import org.mapstruct.*

//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface EntityMapper {

    fun toEmployeeDTO(employee: Employee): EmployeeDTO

    fun toEmployee(employeeDTO: EmployeeDTO): Employee

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    fun updateEmployeeFields(@MappingTarget employee: Employee, employeeDTO: EmployeeDTO)
}