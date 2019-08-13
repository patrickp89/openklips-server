package org.openklips.server.service

import org.openklips.server.model.StudyProgramme
import org.openklips.server.persistence.StudyProgrammeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.transaction.Transactional

/**
 * A service that encapsulates all logic regarding courses.
 */
@Component
class StudyProgrammeService(private val studyProgrammeRepository: StudyProgrammeRepository) {

    private val log: Logger = LoggerFactory.getLogger(StudyProgrammeService::class.java)

    /**
     * Persists a new study programme.
     */
    @Transactional
    fun createStudyProgramme(studyProgramme: StudyProgramme): StudyProgramme {
        log.debug("Saving study programme ($studyProgramme)...")
        val savedStudyProgramme = studyProgrammeRepository.save(studyProgramme)
        log.debug("Done! savedStudyProgramme.id = ${savedStudyProgramme.id}")
        return savedStudyProgramme
    }

    /**
     * Retrieves all study programmes.
     */
    @Transactional
    fun getAllStudyProgrammes(): List<StudyProgramme> {
        log.debug("Looking up all study programmes...")
        val allCourses = studyProgrammeRepository
                .findAll()
                .toList()
        log.debug("Found ${allCourses.size} study programmes!")
        return allCourses
    }

}
