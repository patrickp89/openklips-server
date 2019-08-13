package org.openklips.server.persistence

import org.openklips.server.model.StudyProgramme
import org.springframework.data.repository.CrudRepository

interface StudyProgrammeRepository : CrudRepository<StudyProgramme, Long> {}
