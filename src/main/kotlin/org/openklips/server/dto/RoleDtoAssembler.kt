package org.openklips.server.dto

import org.openklips.server.model.Role
import org.openklips.server.model.dto.RoleDto

class RoleDtoAssembler: DtoAssembler<Role, RoleDto> {
    override fun assemble(source: Role): RoleDto {
        val roleDto = RoleDto()
        return roleDto
    }
}