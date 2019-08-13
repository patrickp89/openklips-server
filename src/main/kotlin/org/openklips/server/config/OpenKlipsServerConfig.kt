package org.openklips.server.config

import org.openklips.server.service.CourseService
import org.openklips.server.service.StudyProgrammeService
import org.openklips.server.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenKlipsServerConfig {

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

}
