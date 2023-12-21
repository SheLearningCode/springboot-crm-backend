package de.novatecgmbh.talenthubch1.controller

import de.novatecgmbh.talenthubch1.api.CourseApi
import de.novatecgmbh.talenthubch1.api.dto.request.CourseRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseResponseDto
import de.novatecgmbh.talenthubch1.controller.mapper.CourseMapper
import de.novatecgmbh.talenthubch1.service.CourseService
import de.novatecgmbh.talenthubch1.service.EnrolmentService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
class CourseController(
    private val courseService: CourseService,
    private val enrolmentService: EnrolmentService,
    private val courseMapper: CourseMapper
) : CourseApi {
    override fun getCourse(id: Long): CourseDetailedResponseDto =
        courseMapper.courseToCourseDetailedResponseDto(
            courseService.findById(id),
            enrolmentService.findStudentsByCourseId(id)
        )

    override fun createCourse(courseRequestDto: CourseRequestDto): CourseResponseDto =
        courseMapper.courseToCourseResponseDto(
            courseService.create(
                courseMapper.courseRequestDtoToCourse(
                    courseRequestDto
                )
            )
        )

    override fun updateCourse(id: Long, courseUpdateDto: CourseRequestDto): CourseResponseDto =
        courseMapper.courseToCourseResponseDto(
            courseService.update(
                courseMapper.updateCourseWithCourseRequestDto(
                    courseService.findById(id),
                    courseUpdateDto
                )
            )
        )

    override fun deleteCourse(id: Long) =
        courseService.delete(
            courseService.findById(id)
        )
}
