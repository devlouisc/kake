package dev.louisc.kake.dependency

data class DependencyNode internal constructor(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val children: List<DependencyNode> = listOf()
)
