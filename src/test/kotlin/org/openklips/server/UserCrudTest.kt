package org.openklips.server

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.openklips.server.model.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.hamcrest.Matchers.`is` as Is

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserCrudTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun testGetUser() {
        val testuserName = "mustermannm1"
        val response: ResponseEntity<UserDto> = restTemplate
                .getForEntity("/user/{username}", UserDto::class.java, testuserName)
        assertThat(response, Is(not(nullValue())))
        assertThat(response.statusCode, Is(HttpStatus.OK))
        assertThat(response.body, Is(not(nullValue())))

        val userDto: UserDto = response.body
                ?: throw NullPointerException("This should never happen.")
        assertThat(userDto.firstName, Is("Max"))
    }

    @Test
    @Ignore
    fun testGetUserRoles() {
        val testuserName = "mustermannm1"
        val response: ResponseEntity<Array<UserDto>> = restTemplate
                .getForEntity("/user/{username}/roles", Array<UserDto>::class.java, testuserName) // TODO: this shouldn't be UserDTOs?!?!
        assertThat(response, Is(not(nullValue())))
        assertThat(response.statusCode, Is(HttpStatus.OK))
        assertThat(response.body, Is(not(nullValue())))

        val userDtos: Array<UserDto> = response.body
                ?: throw NullPointerException("This should never happen.")
        assertThat(userDtos.size, Is(1))
    }

}
