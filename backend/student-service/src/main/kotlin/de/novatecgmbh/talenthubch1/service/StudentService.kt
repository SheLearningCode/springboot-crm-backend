package de.novatecgmbh.talenthubch1.service

import de.novatecgmbh.talenthubch1.persistence.StudentRepository
import de.novatecgmbh.talenthubch1.persistence.entity.Student
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun findById(id: Long): Student =
        studentRepository.findById(id).orElseThrow { NoSuchElementException("No student found for id '$id'") }

    fun create(student: Student): Student = studentRepository.save(student)

    fun update(student: Student): Student = studentRepository.save(student)

    fun delete(student: Student) =
        studentRepository.delete(student)
            .also { kafkaTemplate.send("student-deleted", student.id.toString()) }
}
