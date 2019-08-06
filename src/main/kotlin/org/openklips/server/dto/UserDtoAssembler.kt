package org.openklips.server.dto

import org.openklips.server.model.User
import org.openklips.server.model.dto.RoleDto
import org.openklips.server.model.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserDtoAssembler(
        private val addressDtoAssembler: AddressDtoAssembler,
        private val roleDtoAssembler: RoleDtoAssembler
) : DtoAssembler<User, UserDto> {

    override fun assemble(source: User): UserDto {
        val addressDto = addressDtoAssembler.assemble(source.address)
        val roleDtos: List<RoleDto> = source
                .roles
                .map { r -> roleDtoAssembler.assemble(r) }
                .toList()

        val userDto = UserDto()
        userDto.id = source.id
        userDto.firstName = source.firstName
        userDto.lastName = source.lastName
        userDto.title = source.title
        userDto.username = source.username

        userDto.address = addressDto
        userDto.roles = roleDtos
        return userDto
    }

}
