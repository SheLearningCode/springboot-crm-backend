package de.novatecgmbh.talenthubch1.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Enrolment(
    @Id
    @GeneratedValue
    val id: Long,

    @OneToOne
    val student: Student,

    @OneToOne
    val course: Course
)
