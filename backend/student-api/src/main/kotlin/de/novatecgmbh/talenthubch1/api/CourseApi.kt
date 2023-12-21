package de.novatecgmbh.talenthubch1.api

import de.novatecgmbh.talenthubch1.api.dto.request.CourseRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.CourseResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/v1/courses")
@FeignClient(name = "courses")
interface CourseApi {

    @Operation(summary = "Get Course by id")
    @ApiResponse(responseCode = "200", description = "Course found")
    @ApiResponse(responseCode = "404", description = "Course not found")
    @GetMapping("/{id}")
    fun getCourse(@PathVariable id: Long): CourseDetailedResponseDto

    @Operation(summary = "Create new course")
    @ApiResponse(responseCode = "201", description = "Course created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    fun createCourse(@RequestBody @Valid courseRequestDto: CourseRequestDto): CourseResponseDto

    @Operation(summary = "Update existing course")
    @ApiResponse(responseCode = "200", description = "Course updated")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Course not found")
    @PatchMapping("/{id}")
    fun updateCourse(
        @PathVariable id: Long,
        @RequestBody @Valid courseUpdateDto: CourseRequestDto
    ): CourseResponseDto

    @Operation(summary = "Delete existing course")
    @ApiResponse(responseCode = "200", description = "Course deleted")
    @ApiResponse(responseCode = "404", description = "Course not found")
    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable id: Long)
}
