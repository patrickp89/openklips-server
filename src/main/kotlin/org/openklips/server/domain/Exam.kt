package org.openklips.server.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Exam(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0

        // TODO: ...
)
