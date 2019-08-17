package org.openklips.server.service

import org.openklips.server.model.Enrollment
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

    /**
     * Retrieves a single student for his/her student ID (Matrikelnummer)
     *
     * @param studentId the student ID
     */
    @Transactional
    fun getStudentForStudentId(studentId: Long): Optional<Student> {
        log.debug("Looking up student by his/her studentId (Matrikelnummer): $studentId")
        val studentOptional = studentRepository.findByStudentId(studentId)
        if (studentOptional.isPresent) {
            val student = studentOptional.get()
            log.debug("Found student: $student!")
        } else {
            log.debug("Couldn't find a student for studentId '$studentId'!")
        }
        return studentOptional
        // TODO: sum type = T x NotFound
    }

    /**
     * Retrieves all enrollments for a given student.
     *
     * @param studentId the student's student ID (Matrikelnummer)
     */
    @Transactional
    fun getStudentEnrollments(studentId: Long): List<Enrollment>? {
        val studentResult = studentRepository.findByStudentId(studentId)
        return if (studentResult.isPresent) {
            val student = studentResult.get()
            log.debug("Found student $student !")
            val enrollments = student.enrollments
            log.debug("...with ${enrollments.size} enrollments!")
            enrollments
        } else {
            null
        }
    }

}
