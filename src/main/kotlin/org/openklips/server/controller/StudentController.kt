package org.openklips.server.controller

import org.openklips.server.model.Enrollment
import org.openklips.server.model.Student
import org.openklips.server.service.StudentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(private val studentService: StudentService) {

    val log: Logger = LoggerFactory.getLogger(StudentController::class.java)

    @RequestMapping("/student/{studentId}")
    fun getStudent(@PathVariable studentId: Long): ResponseEntity<Student> {
        log.debug("The studentID (Matrikelnummer) was: $studentId")
        val student = studentService.getStudentForStudentId(studentId)
        // TODO: when(student) {...}
        return ResponseEntity.of(student)
    }

    @RequestMapping("/student/{studentId}/enrollment")
    fun getStudentEnrollments(@PathVariable studentId: Long): ResponseEntity<List<Enrollment>> {
        log.debug("The studentID (Matrikelnummer) was: $studentId")
        val enrollments: List<Enrollment> = studentService.getStudentEnrollments(studentId)
        // TODO: DTO!
        return ResponseEntity.ok(enrollments)
    }

}
