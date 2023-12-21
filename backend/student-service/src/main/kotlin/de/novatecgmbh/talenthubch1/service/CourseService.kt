package de.novatecgmbh.talenthubch1.service

import de.novatecgmbh.talenthubch1.persistence.CourseRepository
import de.novatecgmbh.talenthubch1.persistence.entity.Course
import org.springframework.stereotype.Service

@Service
class CourseService(
    private val courseRepository: CourseRepository
) {

    fun findById(id: Long): Course =
        courseRepository.findById(id).orElseThrow { NoSuchElementException("No Course found for id '$id'") }

    fun create(course: Course): Course = courseRepository.save(course)

    fun update(course: Course): Course = courseRepository.save(course)

    fun delete(course: Course) = courseRepository.delete(course)
}
