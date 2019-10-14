package org.openklips.server.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * The type of a certain study programme. There might be multiple
 * programmes with the same name, but differing in degree type (e.g.
 * "Information Systems, B.Sc." and "Information Systems, M.Sc.").
 */
@Entity
enum class DegreeType(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val dt: String
) {
    BACHELOR(dt = "BACHELOR"),
    MASTER(dt = "MASTER"),
    PHD(dt = "MASTER")
}
