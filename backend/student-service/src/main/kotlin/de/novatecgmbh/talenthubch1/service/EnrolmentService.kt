package de.novatecgmbh.talenthubch1.service

import de.novatecgmbh.talenthubch1.persistence.EnrolmentRepository
import de.novatecgmbh.talenthubch1.persistence.entity.Course
import de.novatecgmbh.talenthubch1.persistence.entity.Enrolment
import de.novatecgmbh.talenthubch1.persistence.entity.Student
import org.springframework.stereotype.Service

@Service
class EnrolmentService(
    private val enrolmentRepository: EnrolmentRepository
) {

    fun findById(id: Long): Enrolment =
        enrolmentRepository.findById(id).orElseThrow { NoSuchElementException("No Enrolment found for id '$id'") }

    fun findStudentsByCourseId(id: Long): List<Student> =
        enrolmentRepository.findAllByCourseId(id).map { it.student }

    fun findCoursesByStudentId(id: Long): List<Course> =
        enrolmentRepository.findAllByStudentId(id).map { it.course }

    fun create(enrolment: Enrolment): Enrolment = enrolmentRepository.save(enrolment)
}
