package com.tom.demo.kotlin.springboot.controller

import com.tom.demo.kotlin.springboot.dto.EmployeeDTO
import com.tom.demo.kotlin.springboot.model.Employee
import com.tom.demo.kotlin.springboot.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/employees")
class EmployeeController(@Autowired private val employeeService: EmployeeService) {

    //gets all employees
    @GetMapping
    fun getAllEmployees(): ResponseEntity<List<Employee>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(employeeService.getAllEmployees())


    //gets the requested employee
    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Int): Employee {
        return employeeService.getEmployeeById(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "This employee does not exist $id"
        )
    }


    //creates a new employee
    @PostMapping
    fun saveEmployees(@RequestBody employeeDTO: EmployeeDTO): ResponseEntity<Employee> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(employeeService.saveEmployees(employeeDTO))

    }

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable id: Int, @RequestBody employeeDTO: EmployeeDTO): ResponseEntity<Employee> {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(id, employeeDTO))
    }


    // deletes an existing employee
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Int): ResponseEntity<Any> {
        try {
            employeeService.deleteEmployee(id)
        } catch (e: Exception) {
        }
        return ResponseEntity.noContent().build()

    }

}