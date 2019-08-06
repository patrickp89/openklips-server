package org.openklips.server.dto

import org.openklips.server.model.Instructor
import org.openklips.server.model.Role
import org.openklips.server.model.Student
import org.openklips.server.model.dto.RoleDto
import org.openklips.server.model.dto.RoleType
import org.springframework.stereotype.Component

@Component
class RoleDtoAssembler : DtoAssembler<Role, RoleDto> {

    override fun assemble(source: Role): RoleDto {
        computeRoleType(source).fold({ t ->
            return assembleRoleVariant(t, source)
        }, { e ->
            throw e
        })
    }

    private fun computeRoleType(role: Role): Result<RoleType> {
        return when (role) {
            is Instructor -> Result.success(RoleType.INSTRUCTOR)
            is Student -> Result.success(RoleType.STUDENT)
            else -> Result.failure(Exception("Unhandled role type!"))
        }
    }

    private fun assembleRoleVariant(roleType: RoleType, source: Role): RoleDto {
        val roleDto = RoleDto()
        roleDto.id = source.id

        return when (roleType) {
            RoleType.STUDENT -> assembleStudent(source as Student, roleDto)
            RoleType.INSTRUCTOR -> assembleInstructor(source as Instructor, roleDto)
        }
    }

    private fun assembleStudent(source: Student, studentDto: RoleDto): RoleDto {
        studentDto.roleType = RoleType.STUDENT
        // TODO: studentDto.studentId = source.studentId
        return studentDto
    }

    private fun assembleInstructor(source: Instructor, instructorDto: RoleDto): RoleDto {
        instructorDto.roleType = RoleType.INSTRUCTOR
        // TODO: ...
        return instructorDto
    }

}
