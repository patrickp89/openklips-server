package org.openklips.server.service

import org.openklips.server.model.Role
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UserService {

    val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun getRoles(username: String): List<Role> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
