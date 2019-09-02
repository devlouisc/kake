plugins { id("org.jetbrains.kotlin.jvm") version "1.3.50" }

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
tasks.test{ useJUnitPlatform() }
