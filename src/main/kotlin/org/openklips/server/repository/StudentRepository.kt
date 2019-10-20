package org.openklips.server.persistence

import org.openklips.server.domain.Student
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StudentRepository : CrudRepository<Student, Long> {

    fun findByStudentId(studentId: Long): Optional<Student>

}
