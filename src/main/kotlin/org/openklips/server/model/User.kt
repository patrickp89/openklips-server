package org.openklips.server.model

import javax.persistence.*

@Entity
class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val firstName: String,
        val lastName: String,
        val title: String?,

        @OneToOne(cascade = [CascadeType.ALL])
        val address: Address,

        @Column(unique = true)
        val username: String, // the username, used for logins

        @OneToOne(cascade = [CascadeType.ALL]) // TODO: make OneToMany!
        val role: Role
)
