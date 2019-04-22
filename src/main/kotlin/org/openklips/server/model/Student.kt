package org.openklips.server.model

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
        val studentId: Long, // the "Matrikelnummer"

        @OneToMany(cascade = [CascadeType.ALL])
        val enrollments: List<Enrollment>

        // TODO: status -> model a students's lifecycle!
) : Role()
