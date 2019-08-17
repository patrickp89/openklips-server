package org.openklips.server.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.openklips.server.service.CourseService
import org.openklips.server.service.StudyProgrammeService
import org.openklips.server.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class OpenKlipsServerConfig {

    companion object {
        private val defaultZoneId = "ECT"
        val defaultTimezoneId: java.time.ZoneId = java.time.ZoneId.of(defaultZoneId, java.time.ZoneId.SHORT_IDS)
    }

    /**
     * A database initializer used for testing purposes. Disable in production!
     */
    @Bean
    fun databaseInitializer(
            userService: UserService,
            courseService: CourseService,
            studyProgrammeService: StudyProgrammeService
    ): CommandLineRunner {
        return CommandLineRunner {
            val dbInitializer = OpenKlipsDatabaseInitializer(
                    userService = userService,
                    courseService = courseService,
                    studyProgrammeService = studyProgrammeService
            )
            dbInitializer.initializeAll()
        }
    }

    /**
     * A timezone-aware Jackson JSON marshaller.
     */
    @Bean
    fun jacksonMarshaller(timeZone: TimeZone): ObjectMapper {
        // serializing/deserializing dates should be based on our default timezone:
        val objectMapper = ObjectMapper()
        objectMapper.setTimeZone(timeZone)
        objectMapper.registerModule(ParameterNamesModule())
        objectMapper.registerModule(Jdk8Module())
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }

    /**
     * The global timezone.
     */
    @Bean
    fun timezone(): TimeZone? {
        // TODO: the time zone should be configurable! add a @Value("${timezone}") parameter!
        return TimeZone.getTimeZone(defaultTimezoneId)
    }

}
