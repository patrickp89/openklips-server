package org.openklips.server.model

import javax.persistence.*

@Entity
class Student(
        @Column(unique = true)
        val studentId: Long // the "Matrikelnummer"
) : Role()
