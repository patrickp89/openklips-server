package org.openklips.server.dto

import org.springframework.stereotype.Component

@Component
class StudentDtoAssembler(
        private val enrollmentDtoAssembler: EnrollmentDtoAssembler
) : DtoAssembler<org.openklips.server.model.Student, org.openklips.server.model.dto.Student> {

    override fun assemble(source: org.openklips.server.model.Student): org.openklips.server.model.dto.Student {
        val enrollmentDtos: List<org.openklips.server.model.dto.Enrollment> = source
                .enrollments
                .map { enrollmentDtoAssembler.assemble(it) }
                .toList()

        val studentDto = org.openklips.server.model.dto.Student()
        studentDto.id = source.studentId
        studentDto.enrollments = enrollmentDtos

        return studentDto
    }

}
