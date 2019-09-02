package dev.louisc.kake.depmgmt

data class ProjectJson(
        val groupId: String = "",
        val artifactId: String = "",
        val version: String = "",
        val dependencies: List<String>? = null,
        val developmentDependencies: List<String>? = null,
        val overridingDependencies: List<String>? = null
)
