package de.novatecgmbh.talenthubch1.controller

import de.novatecgmbh.talenthubch1.api.EnrolmentApi
import de.novatecgmbh.talenthubch1.api.dto.request.EnrolmentRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.EnrolmentResponseDto
import de.novatecgmbh.talenthubch1.controller.mapper.EnrolmentMapper
import de.novatecgmbh.talenthubch1.service.CourseService
import de.novatecgmbh.talenthubch1.service.EnrolmentService
import de.novatecgmbh.talenthubch1.service.StudentService
import org.springframework.web.bind.annotation.RestController

@RestController
class EnrolmentController(
    val enrolmentService: EnrolmentService,
    val studentService: StudentService,
    val courseService: CourseService,
    val enrolmentMapper: EnrolmentMapper
) : EnrolmentApi {

    override fun getEnrolment(id: Long): EnrolmentResponseDto =
        enrolmentMapper.enrolmentToEnrolmentResponseDto(enrolmentService.findById(id))

    override fun createEnrolment(enrolmentRequestDto: EnrolmentRequestDto): EnrolmentResponseDto =
        enrolmentMapper.enrolmentToEnrolmentResponseDto(
            enrolmentService.create(
                enrolmentMapper.dataToEnrolment(
                    studentService.findById(enrolmentRequestDto.studentId),
                    courseService.findById(enrolmentRequestDto.courseId)
                )
            )
        )
}
