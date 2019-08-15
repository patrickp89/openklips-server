package org.openklips.server.dto

import org.openklips.server.config.OpenKlipsServerConfig
import org.openklips.server.model.Enrollment
import org.openklips.server.model.dto.EnrollmentDto
import org.springframework.stereotype.Component

@Component
class EnrollmentDtoAssembler : DtoAssembler<Enrollment, EnrollmentDto> {

    override fun assemble(source: Enrollment): EnrollmentDto {
        val enrollmentDto = EnrollmentDto()
        enrollmentDto.id = source.id
        enrollmentDto.startDate = convertDate(source.startDate)
        // TODO: ...
        return enrollmentDto
    }

    private fun convertDate(startDate: java.util.Date): org.threeten.bp.LocalDate {
        return org.threeten.bp.DateTimeUtils
                .toInstant(startDate)
                .atZone(OpenKlipsServerConfig.defaultThreeTenTimezoneId)
                .toLocalDate()
    }

}
