package dev.louisc.kake.dependency.project

data class ProjectJson(
    val groupId: String = "",
    val artifactId: String = "",
    val version: String = "",
    val dependencies: Set<String>? = null,
    val developmentDependencies: Set<String>? = null,
    val overridingDependencies: Set<String>? = null
)
