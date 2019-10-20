package org.openklips.server.dto

import org.openklips.server.domain.Instructor
import org.openklips.server.domain.Role
import org.openklips.server.domain.Student
import org.openklips.server.model.dto.RoleType
import org.springframework.stereotype.Component

@Component
class RoleDtoAssembler : DtoAssembler<Role, org.openklips.server.model.dto.Role> {

    override fun assemble(source: Role): org.openklips.server.model.dto.Role {
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

    private fun assembleRoleVariant(roleType: RoleType, source: Role): org.openklips.server.model.dto.Role {
        val roleDto = org.openklips.server.model.dto.Role()
        roleDto.id = when (roleType) {
            RoleType.INSTRUCTOR -> source.id
            RoleType.STUDENT -> {
                // for a student, we displey his/her studentId:
                if ((source as Student).studentId == null) {
                    source.id
                } else {
                    source.studentId
                }
            }
        }

        return when (roleType) {
            RoleType.STUDENT -> assembleStudent(source as Student, roleDto)
            RoleType.INSTRUCTOR -> assembleInstructor(source as Instructor, roleDto)
        }
    }

    private fun assembleStudent(source: Student, studentDto: org.openklips.server.model.dto.Role): org.openklips.server.model.dto.Role {
        studentDto.roleType = RoleType.STUDENT
        return studentDto
    }

    private fun assembleInstructor(source: Instructor, instructorDto: org.openklips.server.model.dto.Role): org.openklips.server.model.dto.Role {
        instructorDto.roleType = RoleType.INSTRUCTOR
        return instructorDto
    }

}
