package org.openklips.server.controller

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

    @RequestMapping("/student/{id}")
    fun getStudent(@PathVariable id: Long): ResponseEntity<Student> {
        log.debug("ID was: $id")
        val student = studentService.getStudent(id)
        // TODO: when(student) {...}
        return ResponseEntity.of(student)
    }

}
