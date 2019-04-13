package org.openklips.server.service

import org.openklips.server.model.Address
import org.openklips.server.model.Student
import org.openklips.server.model.User
import org.openklips.server.persistence.StudentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class StudentService(val studentRepository: StudentRepository) {

    val log: Logger = LoggerFactory.getLogger(StudentService::class.java)

    fun getStudentCount(): Long {
        log.debug("studentRepository.count() ...")
        return studentRepository.count()
    }

    fun getStudent(id: Long): Optional<Student> {
        log.debug("studentRepository.findById(id=$id)")
        return studentRepository.findById(id)
        // TODO: sum type = T x NotFound
    }

    fun createStudent(): User {
        val address = Address(country = "DE",
                streetName = "Musterstrasse",
                houseNumber = "4",
                zipCode = "123456",
                additionalDetails = null)

        val role = Student(studentId = 1234)

        val user = User(firstName = "Max",
                lastName = "Mustermann",
                address = address,
                username = "mustermannm1",
                role = role)

        return user
    }
}
