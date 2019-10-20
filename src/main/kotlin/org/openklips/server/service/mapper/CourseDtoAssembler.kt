package org.openklips.server.dto

import org.openklips.server.domain.Course
import org.springframework.stereotype.Component

@Component
class CourseDtoAssembler : DtoAssembler<Course, org.openklips.server.model.dto.Course> {

    override fun assemble(source: Course): org.openklips.server.model.dto.Course {
        val courseDto = org.openklips.server.model.dto.Course()
        courseDto.id = source.id
        courseDto.name = source.name
        courseDto.courseDescription = source.description

        // TODO: ...

        return courseDto
    }
}
