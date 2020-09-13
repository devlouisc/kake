package dev.louisc.kake.dependency

import org.apache.maven.model.Model

data class DependencyNode internal constructor(
    val id: DependencyIdentifier,
    val model: Model,
    val children: List<DependencyNode>,
)
