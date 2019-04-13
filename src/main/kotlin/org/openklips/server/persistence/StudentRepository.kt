package org.openklips.server.persistence

import org.openklips.server.model.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Long> {}
