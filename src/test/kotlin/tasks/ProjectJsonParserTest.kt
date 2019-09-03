package tasks;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectJsonParserTest {

    @Nested
    inner class Parse {

        @Test
        fun `should handle valid project JSON text`() {
            // GIVEN a valid project JSON text
            val projectJsonText = """
                {
                    "groupId": "groupId",
                    "artifactId": "artifactId",
                    "version": "0.0.0",
                    "dependencies": [
                        "dependent-groupId:dependent-artifactId:1.0.0"
                    ],
                    "developmentDependencies": [
                        "development-groupId:development-artifactId:2.0.0"
                    ],
                    "overridingDependencies": [
                        "overriding-groupId:overriding-artifactId:3.0.0"
                    ]
                }
            """.trimIndent()

            // WHEN it is parsed
            val projectJson = ProjectJsonParser.parse(projectJsonText)

            // THEN the resultant project JSON object is returned
            assertThat(projectJson.groupId).isEqualTo("groupId")
            assertThat(projectJson.artifactId).isEqualTo("artifactId")
            assertThat(projectJson.version).isEqualTo("0.0.0")
            assertThat(projectJson.dependencies).isEqualTo(listOf("dependent-groupId:dependent-artifactId:1.0.0"))
            assertThat(projectJson.developmentDependencies).isEqualTo(listOf("development-groupId:development-artifactId:2.0.0"))
            assertThat(projectJson.overridingDependencies).isEqualTo(listOf("overriding-groupId:overriding-artifactId:3.0.0"))
        }

    }

}
