package org.openklips.server.config

import org.openklips.server.model.Address
import org.openklips.server.model.Instructor
import org.openklips.server.model.Student
import org.openklips.server.model.User
import org.openklips.server.service.UserService

class OpenKlipsDatabaseInitializer(private val userService: UserService) {

    /**
     * Initializes the database with default data.
     */
    fun initializeAll() {
        userService.createUser(maxMustermann())
        userService.createUser(lieschenMueller())
    }

    /**
     * Max Mustermann is a student only.
     */
    private fun maxMustermann(): User {
        val address = Address(country = "DE",
                streetName = "Teststrasse",
                houseNumber = "4",
                zipCode = "123456",
                city = "Hamburg",
                additionalDetails = null)

        return User(firstName = "Max",
                lastName = "Mustermann",
                title = null,
                address = address,
                username = "mustermannm1",
                role = Student(studentId = 1234567L))
    }

    /**
     * Lieschen Müller is a professor.
     */
    private fun lieschenMueller(): User {
        val address = Address(country = "DE",
                streetName = "Leet Street",
                houseNumber = "77",
                zipCode = "76543",
                city = "Köln",
                additionalDetails = null)

        return User(firstName = "Lieschen",
                lastName = "Müller",
                title = "Dr.",
                address = address,
                username = "muellerl1",
                role = Instructor())
    }

}
