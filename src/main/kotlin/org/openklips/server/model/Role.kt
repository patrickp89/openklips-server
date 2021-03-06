package org.openklips.server.model

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Role(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
)
