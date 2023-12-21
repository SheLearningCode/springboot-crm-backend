package de.novatecgmbh.talenthubch1.controller

import de.novatecgmbh.talenthubch1.api.StudentApi
import de.novatecgmbh.talenthubch1.api.dto.request.StudentRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentResponseDto
import de.novatecgmbh.talenthubch1.api.dto.update.StudentUpdateDto
import de.novatecgmbh.talenthubch1.controller.mapper.StudentMapper
import de.novatecgmbh.talenthubch1.service.EnrolmentService
import de.novatecgmbh.talenthubch1.service.StudentService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
class StudentController(
    private val studentService: StudentService,
    private val enrolmentService: EnrolmentService,
    private val studentMapper: StudentMapper
) : StudentApi {

    override fun getStudent(@PathVariable id: Long): StudentDetailedResponseDto =
        studentMapper.studentToStudentDetailedResponseDto(
            studentService.findById(id),
            enrolmentService.findCoursesByStudentId(id)
        )

    override fun createStudent(@RequestBody @Valid studentRequestDto: StudentRequestDto): StudentResponseDto =
        studentMapper.studentToStudentResponseDto(
            studentService.create(
                studentMapper.studentRequestDtoToStudent(
                    studentRequestDto
                )
            )
        )

    override fun updateStudent(
        @PathVariable id: Long,
        @RequestBody @Valid studentUpdateDto: StudentUpdateDto
    ): StudentResponseDto =
        studentMapper.studentToStudentResponseDto(
            studentService.update(
                studentMapper.updateStudentWithStudentRequestDto(
                    studentService.findById(id),
                    studentUpdateDto
                )
            )
        )

    override fun deleteStudent(@PathVariable id: Long) =
        studentService.delete(studentService.findById(id))
}
