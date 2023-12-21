package de.novatecgmbh.talenthubch1.api.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CourseRequestDto(
    @field:NotBlank
    @field:Size(min = 1, max = 35)
    val name: String
)
