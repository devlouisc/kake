package dev.louisc.kake.dependency.project

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

object ProjectJsonParser {
    private val idRegex = Regex("""[A-Za-z0-9_\-.]+""")
    private val versionRegex = Regex("""\d\.\d\.\d""")
    private val dependencyRegex = Regex("${idRegex.pattern}:${idRegex.pattern}:${versionRegex.pattern}")

    private val objectMapper: ObjectMapper

    init {
        val jsonFactory = JsonFactory().enable(JsonParser.Feature.ALLOW_COMMENTS)
        val indenter = DefaultIndenter(" ".repeat(4), DefaultIndenter.SYS_LF)

        objectMapper = ObjectMapper(jsonFactory)
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .setDefaultPrettyPrinter(
                DefaultPrettyPrinter()
                    .withArrayIndenter(indenter)
                    .withObjectIndenter(indenter)
            )
    }

    fun parse(projectJsonText: String): ProjectJson {
        val projectJson = objectMapper.readValue(projectJsonText, ProjectJson::class.java)
        return validate(projectJson)
    }

    fun toString(projectJson: ProjectJson): String {
        return objectMapper.writeValueAsString(projectJson)
    }

    private fun validate(projectJson: ProjectJson): ProjectJson {
        val validationErrors = mutableListOf<String>()

        if (idRegex.matchEntire(projectJson.groupId) == null) {
            validationErrors.add("Group ID '${projectJson.groupId}' does not match regex pattern '${idRegex.pattern}'.")
        }

        if (idRegex.matchEntire(projectJson.artifactId) == null) {
            validationErrors.add("Artifact ID '${projectJson.artifactId}' does not match regex pattern '${idRegex.pattern}'.")
        }

        if (versionRegex.matchEntire(projectJson.version) == null) {
            validationErrors.add("Version '${projectJson.version}' does not match regex pattern '${versionRegex.pattern}'.")
        }

        if (projectJson.dependencies != null) {
            for (dependency in projectJson.dependencies) {
                if (dependencyRegex.matchEntire(dependency) == null) {
                    validationErrors.add("Dependency '$dependency' does not match regex pattern '${dependencyRegex.pattern}'.")
                }
            }
        }

        if (projectJson.developmentDependencies != null) {
            for (dependency in projectJson.developmentDependencies) {
                if (dependencyRegex.matchEntire(dependency) == null) {
                    validationErrors.add("Development dependency '$dependency' does not match regex pattern '${dependencyRegex.pattern}'.")
                }
            }
        }

        if (projectJson.overridingDependencies != null) {
            for (dependency in projectJson.overridingDependencies) {
                if (dependencyRegex.matchEntire(dependency) == null) {
                    validationErrors.add("Overriding dependency '$dependency' does not match regex pattern '${dependencyRegex.pattern}'.")
                }
            }
        }

        if (validationErrors.isNotEmpty()) {
            throw ValidationException(validationErrors)
        }

        return projectJson
    }
}
