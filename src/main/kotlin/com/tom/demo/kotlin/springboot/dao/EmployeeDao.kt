package com.tom.demo.kotlin.springboot.dao

import com.tom.demo.kotlin.springboot.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDao : JpaRepository<Employee, Int> {


}
