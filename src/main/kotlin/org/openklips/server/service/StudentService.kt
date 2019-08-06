package org.openklips.server.service

import org.openklips.server.model.Student
import org.openklips.server.persistence.StudentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.transaction.Transactional

/**
 * A service that encapsulates all logic regarding students.
 */
@Component
class StudentService(private val studentRepository: StudentRepository) {

    private val log: Logger = LoggerFactory.getLogger(StudentService::class.java)

    @Transactional
    fun getStudentCount(): Long {
        log.debug("studentRepository.count() ...")
        return studentRepository.count()
    }

    @Transactional
    fun getStudent(id: Long): Optional<Student> {
        log.debug("studentRepository.findById(id=$id)")
        return studentRepository.findById(id)
        // TODO: sum type = T x NotFound
    }

}
