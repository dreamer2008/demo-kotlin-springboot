package com.tom.demo.kotlin.springboot.service

import com.tom.demo.kotlin.springboot.dao.EmployeeDao
import com.tom.demo.kotlin.springboot.dto.EmployeeDTO
import com.tom.demo.kotlin.springboot.exception.ResourceNotFoundException
import com.tom.demo.kotlin.springboot.model.Employee
import com.tom.demo.kotlin.springboot.util.EntityMapper
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService(@Autowired private val employeeDao: EmployeeDao) {

    fun getAllEmployees(): List<Employee> =
        employeeDao.findAll()

    fun getEmployeeById(id: Int): Employee? =
        employeeDao.findById(id).orElseThrow { ResourceNotFoundException("Employee not found. id=$id") }

    fun saveEmployees(employeeDTO: EmployeeDTO): Employee {
        val mapper = Mappers.getMapper(EntityMapper::class.java) // or
        var employee = mapper.toEmployee(employeeDTO)
        var date = java.util.Date()
        employee.createdAt = date
        employee.updateAt = date
        return employeeDao.save(employee);
    }

    fun updateEmployee(id: Int, employeeDTO: EmployeeDTO): Employee {
        var employee = employeeDao.findById(id).orElseThrow { ResourceNotFoundException("Employee not found. id=$id") }
        employeeDTO.id = employee.id
        var mapper = Mappers.getMapper(EntityMapper::class.java) // or
        mapper.updateEmployeeFields(employee, employeeDTO)
        employee.updateAt = java.util.Date()
        return employeeDao.save(employee)
    }

    fun deleteEmployee(id: Int) {
        employeeDao.deleteById(id)
    }

}