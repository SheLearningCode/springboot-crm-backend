package de.novatecgmbh.talenthubch1.persistence

import de.novatecgmbh.talenthubch1.persistence.entity.Enrolment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnrolmentRepository : JpaRepository<Enrolment, Long> {

    fun findAllByCourseId(id: Long): List<Enrolment>

    fun findAllByStudentId(id: Long): List<Enrolment>
}
