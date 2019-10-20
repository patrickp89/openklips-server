package org.openklips.server.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

/**
 * A user can have the role "student", where he is eligible to
 * take courses and participate in exams, but must not do any
 * instructions etc.
 */
@Entity
class Student(
        @Column(unique = true)
        var studentId: Long?, // the "Matrikelnummer": might be equal to the (inherited) technical ID "id"

        @OneToMany(cascade = [CascadeType.ALL])
        val enrollments: List<Enrollment>

        // TODO: status -> model a students's lifecycle!
) : Role()
