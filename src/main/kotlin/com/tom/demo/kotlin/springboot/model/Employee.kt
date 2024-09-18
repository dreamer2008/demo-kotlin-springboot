package com.tom.demo.kotlin.springboot.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "employee")
class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @NotBlank
    var firstName: String = "",

    @NotBlank
    var lastName: String = "",

    var email: String = "",

    var phone: String = "",

    var status: Int?,

    var createdAt: Date?,

    var updateAt: Date?
)
