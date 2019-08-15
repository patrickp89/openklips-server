package org.openklips.server.dto

import org.openklips.server.model.Course
import org.openklips.server.model.dto.CourseDto
import org.springframework.stereotype.Component

@Component
class CourseDtoAssembler : DtoAssembler<Course, CourseDto> {

    override fun assemble(source: Course): CourseDto {
        val courseDto = CourseDto()
        courseDto.id = source.id
        courseDto.name = source.name
        courseDto.courseDescription = source.description

        // TODO: ...

        return courseDto
    }
}
