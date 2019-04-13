package org.openklips.server.model

import javax.persistence.*

@Entity
class Student(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(unique = true)
        val studentId: Long // the "Matrikelnummer"
) : Role() {}
