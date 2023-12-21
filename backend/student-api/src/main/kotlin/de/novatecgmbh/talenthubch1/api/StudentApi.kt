package de.novatecgmbh.talenthubch1.api

import de.novatecgmbh.talenthubch1.api.dto.request.StudentRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentDetailedResponseDto
import de.novatecgmbh.talenthubch1.api.dto.response.StudentResponseDto
import de.novatecgmbh.talenthubch1.api.dto.update.StudentUpdateDto
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

@RequestMapping("/v1/students")
@FeignClient(name = "students")
interface StudentApi {

    @Operation(summary = "Get Student by id")
    @ApiResponse(responseCode = "200", description = "Student found")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): StudentDetailedResponseDto

    @Operation(summary = "Create new student")
    @ApiResponse(responseCode = "201", description = "Student created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    fun createStudent(@RequestBody @Valid studentRequestDto: StudentRequestDto): StudentResponseDto

    @Operation(summary = "Update existing student")
    @ApiResponse(responseCode = "200", description = "Student updated")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @PatchMapping("/{id}")
    fun updateStudent(
        @PathVariable id: Long,
        @RequestBody @Valid studentUpdateDto: StudentUpdateDto
    ): StudentResponseDto

    @Operation(summary = "Delete existing student")
    @ApiResponse(responseCode = "200", description = "Student deleted")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long)
}
