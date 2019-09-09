package dev.louisc.kake.dependency

data class Dependency(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val dependencies: Set<Dependency>
)
