package de.novatecgmbh.talenthubch1.persistence

import de.novatecgmbh.talenthubch1.persistence.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long>
