import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.allis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mockito:mockito-all:1.10.19")
    implementation("org.mockito:mockito-junit-jupiter:4.4.0")
    implementation("junit:junit:4.13.1")
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.13.2")
    implementation("org.mockito:mockito-core:4.4.0")
    implementation("junit:junit:4.13.1")
    implementation("junit:junit:4.13.1")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}