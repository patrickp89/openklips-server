package org.openklips.server.model

import javax.persistence.*

class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val firstName: String,
        val lastName: String,

        @OneToOne
        val address: Address,

        @Column(unique = true)
        val username: String, // the username, used for logins

        @OneToOne // TODO: make OneToMany!
        val role: Role
) {}
