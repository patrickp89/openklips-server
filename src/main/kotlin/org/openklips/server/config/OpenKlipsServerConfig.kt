package org.openklips.server.config

import org.openklips.server.dto.AddressDtoAssembler
import org.openklips.server.dto.RoleDtoAssembler
import org.openklips.server.dto.UserDtoAssembler
import org.openklips.server.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenKlipsServerConfig {


    /**
     * A database initializer used for testing purposes. Disable in production!
     */
    @Bean
    fun databaseInitializer(userService: UserService): CommandLineRunner {
        return CommandLineRunner {
            val dbInitializer = OpenKlipsDatabaseInitializer(userService)
            dbInitializer.initializeAll()
        }
    }

    /**
     * A AddressDtoAssembler.
     */
    @Bean
    fun addressDtoAssembler(): AddressDtoAssembler {
        return AddressDtoAssembler()
    }

    /**
     * A RoleDtoAssembler.
     */
    @Bean
    fun roleDtoAssembler(): RoleDtoAssembler {
        return RoleDtoAssembler()
    }

    /**
     * A UserDtoAssembler.
     */
    @Bean
    fun userDtoAssembler(addressDtoAssembler: AddressDtoAssembler,
                         roleDtoAssembler: RoleDtoAssembler): UserDtoAssembler {
        return UserDtoAssembler(addressDtoAssembler, roleDtoAssembler)
    }

}
