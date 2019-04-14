package org.openklips.server.service

import org.openklips.server.model.Student
import org.openklips.server.persistence.StudentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class StudentService(private val studentRepository: StudentRepository) {

    private val log: Logger = LoggerFactory.getLogger(StudentService::class.java)

    fun getStudentCount(): Long {
        log.debug("studentRepository.count() ...")
        return studentRepository.count()
    }

    fun getStudent(id: Long): Optional<Student> {
        log.debug("studentRepository.findById(id=$id)")
        return studentRepository.findById(id)
        // TODO: sum type = T x NotFound
    }

}
