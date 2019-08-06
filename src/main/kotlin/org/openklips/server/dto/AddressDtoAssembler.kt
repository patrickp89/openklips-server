package org.openklips.server.dto

import org.openklips.server.model.Address
import org.openklips.server.model.dto.AddressDto
import org.springframework.stereotype.Component

@Component
class AddressDtoAssembler : DtoAssembler<Address, AddressDto> {

    override fun assemble(source: Address): AddressDto {
        val addressDto = AddressDto()
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
