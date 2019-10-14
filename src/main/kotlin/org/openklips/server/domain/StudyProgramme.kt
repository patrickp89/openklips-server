package org.openklips.server.model

import javax.persistence.*

/**
 * A Study Programme is one of (probably) many programmes a
 * university offers. For instance
 *   "Information Systems, B.Sc. (Examination Regulation 2015)".
 */
@Entity
class StudyProgramme(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val name: String, // e.g. "Wirtschaftsinformatik", or "Humanmedizin"

        @Enumerated(EnumType.STRING)
        val degreeType: DegreeType,

        // TODO: @OneToOne !
        val examinationRegulationVersion: String, // a identifier for a certain version of the exam. regulation

        @OneToMany(cascade = [CascadeType.MERGE])
        val courses: List<Course> // TODO: remodel! A course is part of a certain _Section_!
)
