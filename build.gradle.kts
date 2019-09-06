plugins {
    id("com.github.johnrengelman.shadow") version "5.1.0"
    id("org.jetbrains.kotlin.jvm") version "1.3.50"
    id("org.jlleitschuh.gradle.ktlint") version "8.2.0"
}

group = "dev.louisc"
version = "0.0.0"

repositories { mavenCentral() }

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.9.3")
    implementation("commons-cli:commons-cli:1.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")
    testImplementation("org.assertj:assertj-core:3.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}

tasks.compileKotlin { kotlinOptions { jvmTarget = "11" } }
tasks.compileTestKotlin { kotlinOptions { jvmTarget = "11" } }
tasks.test { useJUnitPlatform() }
tasks.jar { manifest { attributes("Main-Class" to "dev.louisc.kake.MainKt") } }
tasks.shadowJar { baseName = "kake"; classifier = null; version = null }
