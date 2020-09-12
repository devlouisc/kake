package dev.louisc.kake.project

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParseProjectJsonTest {

    @Nested
    inner class ParseProjectJson {

        @Test
        fun `should handle valid project JSON text`() {
            // GIVEN a valid project JSON text
            val projectJsonText = (
                """
                // project.json
                {
                    "groupId": "dev.louisc",
                    "artifactId": "kake",
                    "version": "0.0.0",
                    "main": "dev.louisc.kake.MainKt",
                    "repositories": [
                        "https://repo.maven.apache.org/maven2/",
                        "https://jcenter.bintray.com/"
                    ],
                    "dependencies": {
                        "compile": [
                            "com.fasterxml.jackson.core:jackson-databind:2.9.9.3",
                            "commons-cli:commons-cli:1.4",
                            "org.jetbrains.kotlin:kotlin-stdlib:1.3.50",
                            "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1"
                        ],
                        "test": [
                            "org.assertj:assertj-core:3.13.2",
                            "org.junit.jupiter:junit-jupiter-api:5.5.1",
                            "org.junit.jupiter:junit-jupiter-engine:5.5.1"
                        ],
                        "runtime": [],
                        "provided": []
                    },
                    "taskDependencies": []
                }
                """
                ).trimIndent()

            // WHEN it is parsed
            val projectJson = parseProjectJson(projectJsonText)

            // THEN the resultant project JSON object is returned
            assertThat(projectJson.groupId).isEqualTo("dev.louisc")
            assertThat(projectJson.artifactId).isEqualTo("kake")
            assertThat(projectJson.version).isEqualTo("0.0.0")
            assertThat(projectJson.main).isEqualTo("dev.louisc.kake.MainKt")
            assertThat(projectJson.repositories).isEqualTo(
                setOf(
                    "https://repo.maven.apache.org/maven2/",
                    "https://jcenter.bintray.com/"
                )
            )
            assertThat(projectJson.dependencies["compile"]).isEqualTo(
                setOf(
                    "com.fasterxml.jackson.core:jackson-databind:2.9.9.3",
                    "commons-cli:commons-cli:1.4",
                    "org.jetbrains.kotlin:kotlin-stdlib:1.3.50",
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1"
                )
            )
            assertThat(projectJson.dependencies["test"]).isEqualTo(
                setOf(
                    "org.assertj:assertj-core:3.13.2",
                    "org.junit.jupiter:junit-jupiter-api:5.5.1",
                    "org.junit.jupiter:junit-jupiter-engine:5.5.1"
                )
            )
            assertThat(projectJson.dependencies["runtime"]).isEqualTo(emptySet<String>())
            assertThat(projectJson.dependencies["provided"]).isEqualTo(emptySet<String>())
            assertThat(projectJson.taskDependencies).isEqualTo(emptySet<String>())
        }
    }
}
