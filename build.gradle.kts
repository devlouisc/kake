plugins {
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
}

group = "dev.louisc"
version = "0.0.0"

repositories { mavenCentral() }

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.2")
    implementation("commons-cli:commons-cli:1.4")
    implementation("org.apache.maven:maven-model:3.6.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    testImplementation("org.assertj:assertj-core:3.17.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks.compileKotlin { kotlinOptions { jvmTarget = "14" } }
tasks.compileTestKotlin { kotlinOptions { jvmTarget = "14" } }
tasks.test { useJUnitPlatform() }
tasks.jar { manifest { attributes("Main-Class" to "dev.louisc.kake.MainKt") } }
tasks.shadowJar { baseName = "kake"; classifier = null; version = null }
