package de.novatecgmbh.talenthubch1.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity
data class Course(
    @field:Id
    @field:GeneratedValue
    val id: Long,

    @field:NotBlank
    var name: String,

    @field:OneToMany
    val enrolments: MutableSet<Enrolment>
)
