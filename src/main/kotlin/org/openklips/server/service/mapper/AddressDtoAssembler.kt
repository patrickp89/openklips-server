package org.openklips.server.dto

import org.openklips.server.domain.Address
import org.springframework.stereotype.Component

@Component
class AddressDtoAssembler : DtoAssembler<Address, org.openklips.server.model.dto.Address> {

    override fun assemble(source: Address): org.openklips.server.model.dto.Address {
        val addressDto = org.openklips.server.model.dto.Address()
        addressDto.country = source.country
        addressDto.city = source.city
        addressDto.id = source.id
        addressDto.zipCode = source.zipCode
        addressDto.streetName = source.streetName
        addressDto.houseNumber = source.houseNumber
        addressDto.additionalDetails = source.additionalDetails
        return addressDto
    }

}
