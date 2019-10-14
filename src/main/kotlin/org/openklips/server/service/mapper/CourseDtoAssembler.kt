package org.openklips.server.dto

import org.springframework.stereotype.Component

@Component
class CourseDtoAssembler : DtoAssembler<org.openklips.server.model.Course, org.openklips.server.model.dto.Course> {

    override fun assemble(source: org.openklips.server.model.Course): org.openklips.server.model.dto.Course {
        val courseDto = org.openklips.server.model.dto.Course()
        courseDto.id = source.id
        courseDto.name = source.name
        courseDto.courseDescription = source.description

        // TODO: ...

        return courseDto
    }
}
