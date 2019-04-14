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
        userService.createUser(bertoltBrecht())
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

        val roles = listOf(Student(studentId = 1234567L))

        return User(firstName = "Max",
                lastName = "Mustermann",
                title = null,
                address = address,
                username = "mustermannm1",
                roles = roles)
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

        val roles = listOf(Instructor())

        return User(firstName = "Lieschen",
                lastName = "Müller",
                title = "Dr.",
                address = address,
                username = "muellerl1",
                roles = roles)
    }

    /**
     * Bertolt Brecht is both a student and a professor.
     */
    private fun bertoltBrecht(): User {
        val address = Address(country = "DE",
                streetName = "Chausseestraße",
                houseNumber = "185",
                zipCode = "10115",
                city = "Berlin",
                additionalDetails = null)


        val roles = listOf(Instructor(), Student(studentId = 654545L))

        return User(firstName = "Bertolt",
                lastName = "Brecht",
                title = "Dr.",
                address = address,
                username = "brechtb33",
                roles = roles)
    }

}
