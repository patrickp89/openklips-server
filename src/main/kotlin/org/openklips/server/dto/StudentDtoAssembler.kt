package org.openklips.server.dto

import org.openklips.server.model.Student
import org.openklips.server.model.dto.EnrollmentDto
import org.openklips.server.model.dto.StudentDto
import org.springframework.stereotype.Component

@Component
class StudentDtoAssembler(
        private val enrollmentDtoAssembler: EnrollmentDtoAssembler
) : DtoAssembler<Student, StudentDto> {

    override fun assemble(source: Student): StudentDto {
        val enrollmentDtos: List<EnrollmentDto> = source
                .enrollments
                .map { enrollmentDtoAssembler.assemble(it) }
                .toList()

        val studentDto = StudentDto()
        studentDto.id = source.studentId
        studentDto.enrollments = enrollmentDtos

        return studentDto
    }

}
