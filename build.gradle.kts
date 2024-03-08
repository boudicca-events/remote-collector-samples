plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management")version "1.1.4"
}

group = "events.boudicca.samples"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("events.boudicca:semantic-conventions:0.3.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

kotlin {
    jvmToolchain(21)
}