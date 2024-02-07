# Student Courses CRM

## Overview

The Student Courses CRM is a web-based application designed to streamline the management of student enrollments in various courses. It facilitates easier accessibility and organization of students and courses, providing a centralized platform for administrators to manage enrollments, track student progress, and administer course materials.

## Purpose

The purpose of this project is twofold:

1. **Organizational Efficiency**: The CRM simplifies the process of managing student enrollments and course offerings. It allows administrators to easily add, remove, and update student records and course details, reducing manual effort and improving organizational efficiency.

2. **Learning and Development**: The project serves as a learning opportunity for developing clean and maintainable code using Kotlin. By implementing CRUD operations and API clients, developers gain hands-on experience in building RESTful APIs and consuming them in client applications.

## Project Structure

The project is organized into two primary components:

## Student API
Located under backend/student-api, this module handles client-side interactions and API endpoints related to student management. The structure includes:

- DTOs: Data transfer objects for requests, responses, and updates related to courses, enrolments, and students.
- CourseRequ.kt, CourseRequestDto.kt, EnrolmentR.kt, EnrolmentRequestDto.kt, StudentReq.kt, StudentRequestDto.kt, CourseResponseDto.kt, StudentResponseDto.kt, StudentUpdateDto.kt
- APIs: Controllers defining the routes and handling HTTP requests.
  CourseApi.kt, EnrolmentApi.kt, StudentApi.kt

## Student Service
Found in backend/student-service, this module encompasses backend logic and data management for student operations. Key components include:

- Controllers: Handling HTTP requests and mapping them to service operations.
CourseController.kt, EnrolmentController.kt, StudentController.kt

- Persistence Layer: Data entities and repositories for managing course, enrolment, and student data.
CourseRepository.kt, EnrolmentRepository.kt, StudentRepository.kt

- Service Layer: Implementing business logic and performing operations related to courses, enrolments, and students.
CourseService.kt, EnrolmentService.kt, StudentService.kt

- Application Entry Point: The main application class initializing the Spring Boot application.
Talenthubch1Application.kt

## Additional Files
- Docker Configuration: Docker-related files for containerization and deployment.
- Resources: Configuration files and static resources used by the application.
- Test: Contains unit and integration tests for the application components.
- Gradle Configuration: Configuration files for the Gradle build system.
- README.md: Project documentation providing an overview of the project structure and setup instructions.

This separation of concerns allows for easier scalability, maintainability, and deployment of the application. 
In a microservices architecture, applications are structured as a collection of loosely coupled services, each responsible for a specific business function. These services communicate with each other via APIs, typically using lightweight protocols such as HTTP or messaging queues.

## Microservices architectures offer several advantages, including:

Scalability: Individual services can be scaled independently based on demand, allowing for better resource utilization and improved performance.

Flexibility: Services can be developed, deployed, and updated independently, enabling faster development cycles and easier maintenance.

Resilience: Fault isolation and graceful degradation mechanisms help prevent failures in one service from affecting the entire system.

Technology Diversity: Different services can be built using different technologies and programming languages, allowing teams to choose the best tools for each task.

However, microservices architectures also come with challenges, such as increased complexity in managing distributed systems, inter-service communication overhead, and potential data consistency issues.

Overall, the choice of a microservices architecture suggests a modular and scalable approach to building your application, which aligns well with the goals of flexibility and maintainability.


# Building the application

## Prerequisites

For building the application, you need:

- JDK >= 17
- Docker (or other runtime)

## Building

The application can be built by executing the following command:

```shell
./gradlew clean build dockerClean docker --info
```

This will build the sources and a docker image which will be registered in the local docker registry.

# Executing the application

## Using docker compose

The application comes with a docker compose file that relies on the docker image that is built with `./gradlew docker`.
The docker compose file can be used with the following command:

```shell
docker compose -f docker/compose.yml up -d
```

## Tech Stack
Kotlin: Modern programming language with concise syntax and powerful features for building robust applications.
Advantages: Offers null safety, type inference, and functional programming capabilities. Interoperates seamlessly with Java.
Disadvantages: Smaller ecosystem compared to Java. May have longer compile times for large projects.

Gradle: Build automation tool for managing dependencies and building projects.
Advantages: Offers a flexible and extensible build configuration with support for plugins. Provides efficient dependency resolution and caching.
Disadvantages: Steeper learning curve compared to other build tools like Maven. Requires configuration for more complex build scenarios.

PostgreSQL: Open-source relational database management system known for its reliability and performance.
Advantages: Offers ACID compliance, strong data integrity, and support for complex queries. Provides scalability and extensibility for large-scale applications.
Disadvantages: Requires more resources compared to NoSQL databases. May have slower performance for certain types of queries.

Flyway: Database migration tool for managing schema changes and version control.
Advantages: Simplifies database versioning and migration with SQL-based scripts. Supports a wide range of databases and integrates seamlessly with build tools.
Disadvantages: Limited support for complex migration scenarios. Requires careful coordination in team environments to avoid conflicts.

Docker-Compose: Tool for defining and running multi-container Docker applications.
Advantages: Simplifies container orchestration and deployment with declarative YAML syntax. Provides isolation and consistency across different environments.
Disadvantages: Can introduce complexity in managing multiple containers. Requires understanding of networking and resource allocation.

Kafka: Distributed streaming platform for building real-time data pipelines and event-driven applications.
Advantages: Offers high throughput, fault tolerance, and scalability for processing streaming data. Provides support for message partitioning and replication.
Disadvantages: Steeper learning curve compared to traditional message brokers. Requires careful configuration for optimal performance and reliability.

DTO's (Data Transfer Objects): Objects used to transfer data between layers of an application, typically used to encapsulate data and ensure type safety.
Advantages: Promotes loose coupling between layers of an application. Provides a clear separation of concerns and improves maintainability.
Disadvantages: Can lead to increased boilerplate code and redundancy. Requires careful design to avoid bloating and maintainability issues.

Mapstruct: Java-based code generator for mapping Java bean classes.
Advantages: Generates efficient mapping code at compile time, improving performance and reducing runtime overhead. Supports complex mapping scenarios with minimal configuration.
Disadvantages: Requires annotations and configuration to define mapping rules. May introduce complexity for simple mapping scenarios.

Testcontainer: Java library for writing integration tests with Docker containers.
Advantages: Provides lightweight and isolated testing environments. Simplifies setup and teardown of test dependencies.
Disadvantages: Requires Docker runtime for running tests. May increase test execution time compared to in-memory testing.

Api-Client: Component responsible for interacting with external APIs or services.
Advantages: Encapsulates communication logic and provides a unified interface for making API calls. Promotes reusability and maintainability.
Disadvantages: Can introduce coupling with external systems. Requires error handling and resilience mechanisms for handling network failures.

REST: Architectural style for designing networked applications based on the principles of representational state transfer (REST).
Advantages: Promotes scalability, simplicity, and interoperability. Encourages stateless communication and resource-based APIs.
Disadvantages: May lead to over-fetching or under-fetching of data. Requires careful design of resource endpoints and URI structure.

YAML: Human-readable data serialization format used for configuration files and data exchange.
Advantages: Offers a simple and readable syntax for representing complex data structures. Supports comments and preserves data types.
Disadvantages: May have limitations in expressing certain data structures compared to JSON or XML. Requires strict adherence to indentation rules.

## To-Dos and Improvements
- Implement authentication and authorization mechanisms to secure the application and manage user roles.
- Pagination
- Implement a frontend
- Conduct additional testing, including integration tests, to ensure the reliability and scalability of the application.
- Consider adding more monitoring and logging capabilities to track application metrics and diagnose issues in production environments.
