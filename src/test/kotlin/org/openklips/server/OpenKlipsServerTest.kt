package org.openklips.server

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.openklips.server.model.Role
import org.openklips.server.model.Student
import org.openklips.server.model.User
import org.openklips.server.service.StudentService
import org.openklips.server.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.hamcrest.Matchers.`is` as Is

@RunWith(SpringRunner::class)
@SpringBootTest
class OpenKlipsServerTest {

    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var userService: UserService

    @Test
    fun testFetchStudentRole() {
        // non-existent users should yield a null value:
        val nonExistentUser = userService.getUser("mustermannm2")
        assertThat(nonExistentUser, Is(nullValue()))

        // Max Mustermann does exist:
        val user: User? = userService.getUser("mustermannm1")
        assertThat(user, Is(not(nullValue())))
        val maxMustermann: User = user ?: throw Exception("This should not have happened!")

        // what are his roles?
        val roles: List<Role>? = userService.getRoles(maxMustermann.username)
        assertThat(roles, Is(not(nullValue())))
        val maxsRoles: List<Role> = roles ?: throw Exception("This should not have happened!")
        assertThat(maxsRoles.size, Is(1))
        assertThat((maxsRoles[0] is Student), Is(true))
        val studentRole = maxsRoles[0] as Student
        assertThat(studentRole.studentId, Is(1234567L))
    }

}
