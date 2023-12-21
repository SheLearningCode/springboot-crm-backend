package de.novatecgmbh.talenthubch1.controller.mapper

import de.novatecgmbh.talenthubch1.api.dto.request.CourseRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseResponseDto
import de.novatecgmbh.talenthubch1.persistence.entity.Course
import de.novatecgmbh.talenthubch1.persistence.entity.Student
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrolments", expression = "java(Set.of())")
    fun courseRequestDtoToCourse(courseRequestDto: CourseRequestDto): Course

    fun courseToCourseDetailedResponseDto(
        course: Course,
        students: List<Student>
    ): CourseDetailedResponseDto

    fun courseToCourseResponseDto(course: Course): CourseResponseDto

    @Mapping(target = "enrolments", ignore = true)
    fun updateCourseWithCourseRequestDto(
        @MappingTarget course: Course,
        courseRequestDto: CourseRequestDto
    ): Course
}
