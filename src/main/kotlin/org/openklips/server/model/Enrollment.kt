package org.openklips.server.model

import java.util.*
import javax.persistence.*

/**
 * A single student might have multiple enrollments: she can for
 * instance be enrolled as an undergraduate (e.g. as a business
 * student) first, and a graduate student (e.g. MBA) afterwards.
 */
@Entity
class Enrollment(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @ManyToOne(cascade = [CascadeType.MERGE])
        val studyProgramme: StudyProgramme,

        @Temporal(TemporalType.DATE)
        val startDate: Date,

        // TODO: status -> model an enrollment's lifecycle!

        @OneToMany(cascade = [CascadeType.MERGE])
        val courses: List<Course>, // TODO: remodel! A student/enrollment rather has a
                                   // list of tuples (Course,Status,Exam) - this would also serve
                                   // as a proper n-to-m mapping table!

        @OneToMany(cascade = [CascadeType.PERSIST])
        val exams: List<Exam>
)
