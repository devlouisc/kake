package depmgmt

data class ProjectJson(
        val groupId: String,
        val artifactId: String,
        val version: String,
        val dependencies: List<String>?,
        val developmentDependencies: List<String>?,
        val overridingDependencies: List<String>?
)
