package dev.louisc.kake.project

data class ProjectJson(
    val groupId: String = "",
    val artifactId: String = "",
    val version: String = "",
    val main: String = "",
    val repositories: Set<String> = emptySet(),
    val dependencies: Map<String, Set<String>> = emptyMap(),
    val taskDependencies: Set<String> = emptySet()
)
