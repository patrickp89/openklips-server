package org.openklips.server.controller

import org.openklips.server.dto.EnrollmentDtoAssembler
import org.openklips.server.dto.StudentDtoAssembler
import org.openklips.server.model.dto.EnrollmentDto
import org.openklips.server.model.dto.StudentDto
import org.openklips.server.service.StudentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(
        private val studentService: StudentService,
        private val studentDtoAssembler: StudentDtoAssembler,
        private val enrollmentDtoAssembler: EnrollmentDtoAssembler
) {

    val log: Logger = LoggerFactory.getLogger(StudentController::class.java)

    @RequestMapping("/student/{studentId}")
    fun getStudent(@PathVariable studentId: Long): ResponseEntity<StudentDto> {
        log.debug("The studentID (Matrikelnummer) was: $studentId")
        val studentResult = studentService.getStudentForStudentId(studentId)
        return if (studentResult.isPresent) {
            ResponseEntity.ok(
                    studentDtoAssembler
                            .assemble(studentResult.get())
            )
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping("/student/{studentId}/enrollment")
    fun getStudentEnrollments(@PathVariable studentId: Long): ResponseEntity<List<EnrollmentDto>> {
        log.debug("The studentID (Matrikelnummer) was: $studentId")
        val enrollments = studentService.getStudentEnrollments(studentId)
        return if (enrollments == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            val enrollmentDtos: List<EnrollmentDto> = enrollments
                    .map { enrollmentDtoAssembler.assemble(it) }
                    .toList()
            return ResponseEntity.ok(enrollmentDtos)
        }
    }

}
