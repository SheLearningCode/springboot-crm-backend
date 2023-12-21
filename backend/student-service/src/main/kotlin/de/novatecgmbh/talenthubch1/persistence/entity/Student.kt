package de.novatecgmbh.talenthubch1.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["email"])
    ]
)
data class Student(
    @field:Id
    @field:GeneratedValue
    val id: Long,

    @field:NotBlank
    var firstName: String,

    @field:NotBlank
    var lastName: String,

    @field:Email
    var email: String,

    @field:OneToMany
    val enrolments: MutableSet<Enrolment>
)
