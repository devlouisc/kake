package depmgmt;

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
                    "group": "group",
                    "artifact": "artifact",
                    "version": "1.0.0",
                    "dependencies": [
                        "dependent-group:dependent-artifact:2.0.0"
                    ],
                    "developmentDependencies": [
                        "development-group:development-artifact:3.0.0"
                    ],
                    "overridingDependencies": [
                        "overriding-group:overriding-artifact:4.0.0"
                    ]
                }
            """

            // WHEN it is parsed
            val projectJson = ProjectJsonParser.parse(projectJsonText)

            // THEN the resultant project JSON object is returned
            assertThat(projectJson.group).isEqualTo("group")
            assertThat(projectJson.artifact).isEqualTo("artifact")
            assertThat(projectJson.version).isEqualTo("1.0.0")
            assertThat(projectJson.dependencies).isEqualTo(listOf("dependent-group:dependent-artifact:2.0.0"))
            assertThat(projectJson.developmentDependencies).isEqualTo(listOf("development-group:development-artifact:3.0.0"))
            assertThat(projectJson.overridingDependencies).isEqualTo(listOf("overriding-group:overriding-artifact:4.0.0"))
        }

    }

}
