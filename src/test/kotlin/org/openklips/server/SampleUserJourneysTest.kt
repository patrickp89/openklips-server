package org.openklips.server

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.openklips.server.model.dto.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.time.Month
import org.hamcrest.Matchers.`is` as Is

/**
 * A test that mirrors the API calls for different user journeys.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleUserJourneysTest {

    val log: Logger = LoggerFactory.getLogger(SampleUserJourneysTest::class.java)

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    /**
     * Our test user is called "Max Mustermann" and he already has a valid login,
     * his username being "mustermannm1".
     * A) He uses his username to fetch information about his user: what are his roles?
     * B) He found his role (Student) and fetches all information about his student role,
     * including the study programmes, that he is enrolled in.
     */
    @Test
    fun testStudentLoginAndCourseOverview() {
        val testUserName = "mustermannm1"
        val testStudentId = 1234567L

        // A) fetch Max' user info:
        val response: ResponseEntity<User> = restTemplate
                .getForEntity("/user/{username}", User::class.java, testUserName)
        assertThat(response, Is(not(nullValue())))
        assertThat(response.statusCode, Is(HttpStatus.OK))
        assertThat(response.body, Is(not(nullValue())))

        val userDto: User = response.body
                ?: throw NullPointerException("This should never happen.")
        assertThat(userDto.firstName, Is("Max"))
        assertThat(userDto.firstName, Is(not(nullValue())))
        assertThat(userDto.lastName, Is("Mustermann"))
        assertThat(userDto.lastName, Is(not(nullValue())))

        log.debug("Max has ${userDto.roles.size} role(s):")
        userDto.roles.forEach { log.debug("ID: ${it.id}, roleType: ${it.roleType}") }
        assertThat(userDto.roles.size, Is(1))
        val role: Role = userDto
                .roles
                .first()
        assertThat(role.roleType, Is(RoleType.STUDENT))
        assertThat(role.id, Is(testStudentId))

        // B) fetch his student information (incl. his enrollments):
        val studentInfoResponse: ResponseEntity<Student> = restTemplate
                .getForEntity("/student/{studentId}", Student::class.java, testStudentId)
        assertThat(studentInfoResponse, Is(not(nullValue())))
        assertThat(studentInfoResponse.statusCode, Is(HttpStatus.OK))
        assertThat(studentInfoResponse.body, Is(not(nullValue())))

        val studentDto: Student = studentInfoResponse.body
                ?: throw NullPointerException("This should never happen.")
        assertThat(studentDto.id, Is(testStudentId))
        assertThat(studentDto.enrollments.size, Is(1))
        val enrollment = studentDto
                .enrollments
                .first()
        assertThat(enrollment.startDate.year, Is(2019))
        assertThat(enrollment.startDate.month, Is(Month.APRIL))
        assertThat(enrollment.startDate.dayOfMonth, Is(1))
        // TODO: add studyProgramme, courses, and exams to the response!
    }

}
