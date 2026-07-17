plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management")version "1.1.4"
}

group = "events.boudicca.samples"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("events.boudicca:remote-collector-client:0.7.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

kotlin {
    jvmToolchain(21)
}