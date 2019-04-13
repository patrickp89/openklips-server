package org.openklips.server.controller

import org.openklips.server.model.Role
import org.openklips.server.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    @RequestMapping("/user/{username}/roles")
    fun getRoles(@PathVariable username: String): ResponseEntity<List<Role>> {
        log.debug("username was: $username")
        val roles = userService.getRoles(username)
        log.debug("found ${roles.size} roles!")
        return ResponseEntity.ok(roles)
    }

}