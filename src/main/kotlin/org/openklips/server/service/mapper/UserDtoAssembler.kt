package org.openklips.server.dto

import org.openklips.server.domain.User
import org.springframework.stereotype.Component

@Component
class UserDtoAssembler(
        private val addressDtoAssembler: AddressDtoAssembler,
        private val roleDtoAssembler: RoleDtoAssembler
) : DtoAssembler<User, org.openklips.server.model.dto.User> {

    override fun assemble(source: User): org.openklips.server.model.dto.User {
        val addressDto = addressDtoAssembler.assemble(source.address)
        val roleDtos: List<org.openklips.server.model.dto.Role> = source
                .roles
                .map { r -> roleDtoAssembler.assemble(r) }
                .toList()

        val userDto = org.openklips.server.model.dto.User()
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
