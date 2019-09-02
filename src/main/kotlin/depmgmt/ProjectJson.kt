package depmgmt

data class ProjectJson(
        val group: String,
        val artifact: String,
        val version: String,
        val dependencies: List<String>?,
        val developmentDependencies: List<String>?,
        val overridingDependencies: List<String>?
)
