package dev.louisc.kake.project

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

object ProjectJsonParser {
    // TODO: Determine the correct regex.
    private val regex = Regex("""[A-Za-z0-9_\-.]+""")
    private val dependencyRegex = Regex("${regex.pattern}:${regex.pattern}:${regex.pattern}")

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

        if (regex.matchEntire(projectJson.groupId) == null) {
            validationErrors.add("Group ID '${projectJson.groupId}' does not match regex pattern '${regex.pattern}'.")
        }

        if (regex.matchEntire(projectJson.artifactId) == null) {
            validationErrors.add("Artifact ID '${projectJson.artifactId}' does not match regex pattern '${regex.pattern}'.")
        }

        if (regex.matchEntire(projectJson.version) == null) {
            validationErrors.add("Version '${projectJson.version}' does not match regex pattern '${regex.pattern}'.")
        }

        for (dependencyGroup in projectJson.dependencies.values) {
            for (dependency in dependencyGroup) {
                if (dependencyRegex.matchEntire(dependency) == null) {
                    validationErrors.add("Dependency '$dependency' does not match regex pattern '${dependencyRegex.pattern}'.")
                }
            }
        }

        for (dependency in projectJson.taskDependencies) {
            if (dependencyRegex.matchEntire(dependency) == null) {
                validationErrors.add("Task dependency '$dependency' does not match regex pattern '${dependencyRegex.pattern}'.")
            }
        }

        if (validationErrors.isNotEmpty()) {
            throw ValidationException(validationErrors)
        }

        return projectJson
    }
}
