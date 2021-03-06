package org.openklips.server

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.openklips.server.model.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.hamcrest.Matchers.`is` as Is

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseCrudTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun testGetCourses() {
        val response: ResponseEntity<Array<Course>> = restTemplate
                .getForEntity("/course", Array<Course>::class.java)
        assertThat(response, Is(not(nullValue())))
        assertThat(response.statusCode, Is(HttpStatus.OK))
        assertThat(response.body, Is(not(nullValue())))

        val courseDtos: Array<Course> = response.body
                ?: throw NullPointerException("This should never happen.")
        assertThat(courseDtos.size, Is(3))
    }

}
