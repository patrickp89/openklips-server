package org.openklips.server.service

import org.openklips.server.model.Role
import org.openklips.server.model.Student
import org.openklips.server.model.User
import org.openklips.server.persistence.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.transaction.Transactional

/**
 * A service that encapsulates all logic regarding users.
 */
@Component
class UserService(private val userRepository: UserRepository) {

    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    fun getUser(username: String): User? {
        log.debug("Looking up user '$username'...")
        val user = userRepository.findByUsername(username)
        if (user == null) {
            log.info("Couldn't find a user with username '$username'!")
        }
        return user
    }

    @Transactional
    fun createUser(user: User): User {
        log.debug("Saving user ($user)...")
        val savedUser = userRepository.save(user)
        log.debug("Done! savedUser.id = ${savedUser.id}")

        // when creating a new user with the role "Student", the
        // studentId (Matrikelnummer) is optional -> if it was not
        // provided, we use the user's technical ID (from the Hibernate
        // sequence) as a studentId:
        val userIsStudent = savedUser
                .roles
                .filterIsInstance<Student>()
                .isNotEmpty()
        if (userIsStudent) {
            val studentRole = savedUser
                    .roles
                    .filterIsInstance<Student>()
                    .first()
            studentRole.studentId = studentRole.studentId ?: studentRole.id
        }

        return savedUser
    }

    @Transactional
    fun getRoles(username: String): List<Role>? {
        log.debug("Looking up roles for user '$username'...")
        val user = userRepository.findByUsername(username)
        return if (user == null) {
            log.info("Couldn't find a user with username '$username'!")
            null
        } else {
            log.debug("Found ${user.roles.size} role(s)")
            user.roles
        }
    }

}
