package org.openklips.server.dto

import org.openklips.server.config.OpenKlipsServerConfig
import org.openklips.server.model.Enrollment
import org.openklips.server.model.dto.EnrollmentDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class EnrollmentDtoAssembler : DtoAssembler<Enrollment, EnrollmentDto> {

    private val log: Logger = LoggerFactory.getLogger(EnrollmentDtoAssembler::class.java)

    override fun assemble(source: Enrollment): EnrollmentDto {
        log.debug("Creating DTO for enrollment: $source")
        val enrollmentDto = EnrollmentDto()
        enrollmentDto.id = source.id
        enrollmentDto.startDate = toLocalDate(source.startDate)
        // TODO: ...
        return enrollmentDto
    }

    private fun toLocalDate(date: Date?): LocalDate {
        return if (date is java.sql.Date?) {
            date?.toLocalDate()
                    ?: throw NullPointerException("The given date was null!")
        } else {
            date?.toInstant()
                    ?.atZone(OpenKlipsServerConfig.defaultTimezoneId)
                    ?.toLocalDate()
                    ?: throw NullPointerException("The given date was null!")
        }
    }

}
