package de.novatecgmbh.talenthubch1.service

import de.novatecgmbh.talenthubch1.persistence.StudentRepository
import de.novatecgmbh.talenthubch1.persistence.entity.Student
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.kafka.core.KafkaTemplate
import java.util.Optional

class StudentServiceTest {

    private val studentRepository: StudentRepository = mockk()
    private val kafkaTemplate: KafkaTemplate<String, String> = mockk(relaxed = true)
    private val cut: StudentService = StudentService(studentRepository, kafkaTemplate)

    private fun generateStudent() = Student(1, "firstName", "lastName", "mail@mail.com", mutableSetOf())

    @Nested
    inner class FindById {
        @Test
        fun `when student exists then findById returns student`() {
            val student = generateStudent()

            every { studentRepository.findById(1) } returns Optional.of(student)

            val foundStudent = cut.findById(1)

            verify(exactly = 1) { studentRepository.findById(1) }
            assertEquals(student, foundStudent)
        }

        @Test
        fun `when student does not exist then findById throws NoSuchElementException`() {
            every { studentRepository.findById(2) } returns Optional.empty()

            assertThrows<NoSuchElementException> {
                cut.findById(2)
            }

            verify(exactly = 1) { studentRepository.findById(2) }
        }
    }

    @Nested
    inner class Create {
        @Test
        fun `create returns created student`() {
            val student = generateStudent()

            every { studentRepository.save(student) } returns student

            val createdStudent = cut.create(student)

            verify(exactly = 1) { studentRepository.save(student) }
            assertEquals(student, createdStudent)
        }
    }

    @Nested
    inner class Update {
        @Test
        fun `update returns updated student`() {
            val student = generateStudent()

            every { studentRepository.save(student) } returns student

            val updatedStudent = cut.update(student)

            verify(exactly = 1) { studentRepository.save(student) }
            assertEquals(student, updatedStudent)
        }
    }

    @Nested
    inner class Delete {
        @Test
        fun `delete calls repository delete method`() {
            val student = generateStudent()

            every { studentRepository.delete(student) } just runs

            cut.delete(student)

            verify(exactly = 1) { studentRepository.delete(student) }
        }
    }
}
