import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    kotlin("jvm") version "1.6.21"
    `java-library`
    `maven-publish`
}

group = "de.novatec-gmbh"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springDocVersion"] = "1.6.0"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.4")
    implementation("org.springdoc:springdoc-openapi-data-rest:${property("springDocVersion")}")
    implementation("org.springdoc:springdoc-openapi-ui:${property("springDocVersion")}")
    implementation("org.springdoc:springdoc-openapi-kotlin:${property("springDocVersion")}")
}

publishing {
    publications {
        create<MavenPublication>("studentCrmApi") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "localRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
