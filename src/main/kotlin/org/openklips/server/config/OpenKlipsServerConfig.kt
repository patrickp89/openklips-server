package org.openklips.server.config

import org.openklips.server.service.CourseService
import org.openklips.server.service.StudyProgrammeService
import org.openklips.server.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.fasterxml.jackson.databind.ObjectMapper



@Configuration
class OpenKlipsServerConfig {

    companion object {
        private val defaultZoneId = "ECT"
        val defaultThreeTenTimezoneId: org.threeten.bp.ZoneId = org.threeten.bp.ZoneId.of(defaultZoneId, org.threeten.bp.ZoneId.SHORT_IDS)
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
     * A JSR310-capable JSON marshaller.
     */
    @Bean
    fun jacksonMarshaller(): ObjectMapper {
        return ObjectMapper()
//                .registerModule(ParameterNamesModule())
//                .registerModule(Jdk8Module())
//                .registerModule(JavaTimeModule())
    }

}
