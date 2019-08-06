package org.openklips.server.controller

import org.openklips.server.dto.CourseDtoAssembler
import org.openklips.server.model.dto.CourseDto
import org.openklips.server.service.CourseService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(
        private val courseService: CourseService,
        private val courseDtoAssembler: CourseDtoAssembler
) {
    val log: Logger = LoggerFactory.getLogger(CourseController::class.java)

    @RequestMapping("/course/")
    fun getCourses(): ResponseEntity<List<CourseDto>> {
        log.debug("Fetching all courses...")
        val courseDtos = courseService
                .getAllCourses()
                .map { c -> courseDtoAssembler.assemble(c) }
                .toList()
        return ResponseEntity.ok(courseDtos)
    }
}
