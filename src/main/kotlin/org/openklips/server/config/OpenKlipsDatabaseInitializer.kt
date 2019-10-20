package org.openklips.server.config

import org.openklips.server.domain.*
import org.openklips.server.service.CourseService
import org.openklips.server.service.StudyProgrammeService
import org.openklips.server.service.UserService
import java.time.LocalDate
import java.time.Month
import java.util.*

class OpenKlipsDatabaseInitializer(
        private val userService: UserService,
        private val courseService: CourseService,
        private val studyProgrammeService: StudyProgrammeService
) {

    /**
     * Initializes the database with default data.
     */
    fun initializeAll() {
        // create some test courses (these will be browsable without a login):
        courseService.createCourse(effizienteAlgos())
        courseService.createCourse(accounting101())
        val ancientRoman203Course = courseService.createCourse(ancientRoman203())

        // create a couple of test study programmes:
        val mscLiterature = studyProgrammeService.createStudyProgramme(
                studyProgramme = StudyProgramme(
                        name = "Literatur",
                        degreeType = DegreeType.MASTER,
                        examinationRegulationVersion = "PO2015",
                        courses = listOf(ancientRoman203Course)
                )
        )

        // create test users:
        userService.createUser(maxMustermann(listOf(ancientRoman203Course), mscLiterature))
        userService.createUser(lieschenMueller())
        userService.createUser(bertoltBrecht())
    }


    /**
     * Max Mustermann is a student only. He is enrolled in a
     * M.Sc. in Literature.
     */
    private fun maxMustermann(courses: List<Course>, mscLiterature: StudyProgramme): User {
        val address = Address(
                country = "DE",
                streetName = "Teststrasse",
                houseNumber = "4",
                zipCode = "123456",
                city = "Hamburg",
                additionalDetails = null
        )

        val startDate = Date.from(
                LocalDate
                        .of(2019, Month.APRIL, 1)
                        .atStartOfDay(OpenKlipsServerConfig.defaultTimezoneId)
                        .toInstant()
        )
        val litEnrollment = Enrollment(
                startDate = startDate,
                courses = courses,
                exams = listOf(),
                studyProgramme = mscLiterature
        )

        val student = Student(
                studentId = 1234567L,
                enrollments = listOf(litEnrollment)
        )

        return User(
                firstName = "Max",
                lastName = "Mustermann",
                title = null,
                address = address,
                username = "mustermannm1",
                roles = listOf(student)
        )
    }

    /**
     * Lieschen Müller is a professor.
     */
    private fun lieschenMueller(): User {
        val address = Address(
                country = "DE",
                streetName = "Leet Street",
                houseNumber = "77",
                zipCode = "76543",
                city = "Köln",
                additionalDetails = null
        )

        return User(
                firstName = "Lieschen",
                lastName = "Müller",
                title = "Dr.",
                address = address,
                username = "muellerl1",
                roles = listOf(Instructor())
        )
    }

    /**
     * Bertolt Brecht is both a student and a professor.
     */
    private fun bertoltBrecht(): User {
        val address = Address(
                country = "DE",
                streetName = "Chausseestraße",
                houseNumber = "185",
                zipCode = "10115",
                city = "Berlin",
                additionalDetails = null
        )

        val student = Student(
                studentId = null,
                enrollments = listOf()
        )

        return User(
                firstName = "Bertolt",
                lastName = "Brecht",
                title = "Dr.",
                address = address,
                username = "brechtb33",
                roles = listOf(Instructor(), student)
        )
    }

    /**
     * A comp sci course on algorithms.
     */
    private fun effizienteAlgos(): Course {
        return Course(
                name = "Effiziente Algos",
                description = "Learn about efficient algorithms for combinatoriaal optimization problems."
        )
    }

    /**
     * A course on money, money, money.
     */
    private fun accounting101(): Course {
        return Course(
                name = "Accounting 101",
                description = "Balance sheets, income and earnings. TL;DR -> MONEY!."
        )
    }

    /**
     * A course on ancient Roman language... Semper fi!
     */
    private fun ancientRoman203(): Course {
        return Course(
                name = "Ancient Roman Language 203",
                description = "Cicero and the Boys."
        )
    }

}
