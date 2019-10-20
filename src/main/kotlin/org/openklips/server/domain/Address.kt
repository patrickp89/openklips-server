package org.openklips.server.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Address(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val country: String, // TODO: create country enum...
        val zipCode: String,
        val city: String,
        val streetName: String,
        val houseNumber: String,
        val additionalDetails: String?
)
