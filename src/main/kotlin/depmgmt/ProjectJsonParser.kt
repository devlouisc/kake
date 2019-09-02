package depmgmt

import com.google.gson.Gson

object ProjectJsonParser {

    private val idRegex = Regex("""[A-Za-z0-9_\-.]+""")
    private val versionRegex = Regex("""\d\.\d\.\d""")

    fun parse(projectJsonText: String): ProjectJson {
        val projectJson = Gson().fromJson(projectJsonText, ProjectJson::class.java)
        return validate(projectJson)
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
            throw NotImplementedError()
        }

        if (projectJson.developmentDependencies != null) {
            throw NotImplementedError()
        }

        if (projectJson.overridingDependencies != null) {
            throw NotImplementedError()
        }

        if (validationErrors.isNotEmpty()) {
            throw ValidationException(validationErrors)
        }

        return projectJson
    }

}
