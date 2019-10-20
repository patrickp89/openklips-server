package org.openklips.server.domain

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

        @OneToMany(cascade = [CascadeType.ALL])
        val roles: List<Role>
)
