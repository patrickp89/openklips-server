package org.openklips.server

import org.openklips.server.config.ApplicationProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(LiquibaseProperties::class, ApplicationProperties::class)
class OpenKlipsServer

fun main(args: Array<String>) {
    SpringApplication.run(OpenKlipsServer::class.java, *args)
}
