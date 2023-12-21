package de.novatecgmbh.talenthubch1.api.dto.update

import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class StudentUpdateDto(
    @field:Size(min = 1, max = 20)
    var firstName: String?,
    @field:Size(min = 1, max = 20)
    var lastName: String?,
    @field:Email
    var email: String?
)
