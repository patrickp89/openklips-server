package org.openklips.server.persistence

import org.openklips.server.domain.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Long> {

    // TODO: find courses for a specific term

}
