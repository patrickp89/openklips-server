package org.openklips.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OpenKlipsServer

fun main(args: Array<String>) {
    SpringApplication.run(OpenKlipsServer::class.java, *args)
}
