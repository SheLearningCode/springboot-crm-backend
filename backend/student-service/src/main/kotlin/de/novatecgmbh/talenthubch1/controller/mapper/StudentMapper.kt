package de.novatecgmbh.talenthubch1.controller.mapper

import de.novatecgmbh.talenthubch1.api.dto.request.StudentRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentResponseDto
import de.novatecgmbh.talenthubch1.api.dto.update.StudentUpdateDto
import de.novatecgmbh.talenthubch1.persistence.entity.Course
import de.novatecgmbh.talenthubch1.persistence.entity.Student
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrolments", expression = "java(Set.of())")
    fun studentRequestDtoToStudent(studentRequestDto: StudentRequestDto): Student

    fun studentToStudentDetailedResponseDto(
        student: Student,
        courses: List<Course>
    ): StudentDetailedResponseDto

    fun studentToStudentResponseDto(student: Student): StudentResponseDto

    @Mapping(target = "enrolments", ignore = true)
    fun updateStudentWithStudentRequestDto(
        @MappingTarget student: Student,
        studentUpdateDto: StudentUpdateDto
    ): Student
}
