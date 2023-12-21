package de.novatecgmbh.talenthubch1.api

import de.novatecgmbh.talenthubch1.api.dto.request.EnrolmentRequestDto
import de.novatecgmbh.talenthubch1.api.dto.response.EnrolmentResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/v1/enrolments")
@FeignClient(name = "enrolments")
interface EnrolmentApi {

    @Operation(summary = "Get enrolment by id")
    @ApiResponse(responseCode = "200", description = "Enrolment found")
    @ApiResponse(responseCode = "404", description = "Enrolment not found")
    @GetMapping("/{id}")
    fun getEnrolment(@PathVariable id: Long): EnrolmentResponseDto

    @Operation(summary = "Create new enrolment")
    @PostMapping
    fun createEnrolment(@RequestBody @Valid enrolmentRequestDto: EnrolmentRequestDto): EnrolmentResponseDto
}
