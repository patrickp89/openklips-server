package org.openklips.server.service

import org.openklips.server.model.Course
import org.openklips.server.persistence.CourseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.transaction.Transactional

/**
 * A service that encapsulates all logic regarding courses.
 */
@Component
class CourseService(private val courseRepository: CourseRepository) {

    private val log: Logger = LoggerFactory.getLogger(CourseService::class.java)

    /**
     * Persists a new course.
     */
    @Transactional
    fun createCourse(course: Course): Course {
        log.debug("Saving course ($course)...")
        val savedCourse = courseRepository.save(course)
        log.debug("Done! savedCourse.id = ${savedCourse.id}")
        return savedCourse
    }

    /**
     * Retrieves all courses.
     */
    @Transactional
    fun getAllCourses(): List<Course> {
        log.debug("Looking up all courses...")
        val allCourses = courseRepository
                .findAll()
                .toList()
        log.debug("Found ${allCourses.size} courses!")
        return allCourses
    }

}
