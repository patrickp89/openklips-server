package org.openklips.server.dto

import org.openklips.server.model.Address
import org.openklips.server.model.dto.AddressDto

class AddressDtoAssembler: DtoAssembler<Address, AddressDto> {

    override fun assemble(source: Address): AddressDto {
        val addressDto = AddressDto()
        addressDto.country = source.country
        addressDto.city = source.city
        // TODO: ...
        return addressDto
    }

}
