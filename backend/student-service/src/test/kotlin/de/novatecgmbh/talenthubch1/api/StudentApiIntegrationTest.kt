package de.novatecgmbh.talenthubch1.api

import com.fasterxml.jackson.databind.ObjectMapper
import de.novatecgmbh.talenthubch1.api.dto.response.StudentResponseDto
import de.novatecgmbh.talenthubch1.util.IsInstant
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
@DirtiesContext
class StudentApiIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Nested
    inner class Post {
        @Test
        fun `when a student is created then the created student is returned with generated id`() {
            val studentRequest = """
            {
                "email": "test1@test.com",
                "firstName": "Ut officia pariatur",
                "lastName": "pariatur officia"
            }
            """.trimIndent()

            mockMvc.post("/v1/students/") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.id", IsInstanceOf(Int::class.java))
                    jsonPath("$.email", IsEqual("test1@test.com"))
                    jsonPath("$.firstName", IsEqual("Ut officia pariatur"))
                    jsonPath("$.lastName", IsEqual("pariatur officia"))
                }
            }
        }

        @Test
        fun `when a student is created given invalid email address then an explicit error message is returned`() {
            val studentRequest = """
            {
                "email": "an@invalid@email.address",
                "firstName": "Ut officia pariatur",
                "lastName": "pariatur officia"
            }
            """.trimIndent()

            mockMvc.post("/v1/students/") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.timestamp", IsInstant())
                    jsonPath("$.status") { value("400") }
                    jsonPath("$.error") { value("Bad Request") }
                    jsonPath("$.errors.email") { value("must be a well-formed email address") }
                }
            }
        }

        @Test
        fun `when a student is created given invalid first name then an explicit error message is returned`() {
            val studentRequest = """
            {
                "email": "test3@test.com",
                "firstName": "This first name is way too long Ut officia pariatur",
                "lastName": "pariatur officia"
            }
            """.trimIndent()

            mockMvc.post("/v1/students/") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.status") { value("400") }
                    jsonPath("$.error") { value("Bad Request") }
                    jsonPath("$.errors.firstName") { value("size must be between 1 and 20") }
                }
            }
        }

        @Test
        fun `when a student is created given invalid last name then an explicit error message is returned`() {
            val studentRequest = """
            {
                "email": "test4@test.com",
                "firstName": "Ut officia pariatur",
                "lastName": "This last name is way too long pariatur officia"
            }
            """.trimIndent()

            mockMvc.post("/v1/students/") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.status") { value("400") }
                    jsonPath("$.error") { value("Bad Request") }
                    jsonPath("$.errors.lastName") { value("size must be between 1 and 20") }
                }
            }
        }
    }

    @Nested
    inner class Patch {

        @Test
        fun `when a student is updated then the updated student is returned`() {
            val studentRequest = """
            {
                "firstName": "different"
            }
            """.trimIndent()

            mockMvc.patch("/v1/students/2") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.email") { value("mail2@mail.com") }
                    jsonPath("$.firstName") { value("different") }
                    jsonPath("$.lastName") { value("lastName") }
                }
            }
        }

        @Test
        fun `when a student is updated given multiple updated fields then the updated student is returned`() {
            val studentRequest = """
            {
                "firstName": "different",
                "lastName": "other"
            }
            """.trimIndent()

            mockMvc.patch("/v1/students/3") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.email") { value("mail3@mail.com") }
                    jsonPath("$.firstName") { value("different") }
                    jsonPath("$.lastName") { value("other") }
                }
            }
        }

        @Test
        fun `when a student is updated given the student id does not exist then a not found error is returned`() {
            val studentRequest = """
            {
                "firstName": "different",
                "lastName": "other"
            }
            """.trimIndent()

            mockMvc.patch("/v1/students/999999") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    inner class Delete {
        @Test
        fun `when a student is deleted then the student no longer exists`() {
            mockMvc.get("/v1/students/5")
                .andExpect { status { isOk() } }

            mockMvc.delete("/v1/students/5")
                .andExpect { status { isOk() } }

            mockMvc.get("/v1/students/5")
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    inner class PostAndGet {
        @Test
        fun `when a student is created then the created student can be queried`() {
            val studentRequest = """
            {
                "email": "test2@test.com",
                "firstName": "Ut officia pariatur",
                "lastName": "pariatur officia"
            }
            """.trimIndent()

            val responseString = mockMvc.post("/v1/students/") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andReturn().response.contentAsString
            val createdStudent = mapper.readValue(responseString, StudentResponseDto::class.java)

            mockMvc.get("/v1/students/${createdStudent.id}")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content {
                        jsonPath("$.id", IsEqual(createdStudent.id.toInt()))
                        jsonPath("$.email", IsEqual("test2@test.com"))
                        jsonPath("$.firstName", IsEqual("Ut officia pariatur"))
                        jsonPath("$.lastName", IsEqual("pariatur officia"))
                    }
                }
        }
    }

    @Nested
    inner class PatchAndGet {
        @Test
        fun `when a student is updated then the updated student can be queried`() {
            val studentRequest = """
            {
                "firstName": "different",
                "lastName": "other"
            }
            """.trimIndent()

            mockMvc.patch("/v1/students/4") {
                contentType = MediaType.APPLICATION_JSON
                content = studentRequest
            }.andExpect {
                status { isOk() }
            }

            mockMvc.get("/v1/students/4")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content {
                        jsonPath("$.id", IsEqual(4))
                        jsonPath("$.email", IsEqual("mail4@mail.com"))
                        jsonPath("$.firstName", IsEqual("different"))
                        jsonPath("$.lastName", IsEqual("other"))
                    }
                }
        }
    }
}
