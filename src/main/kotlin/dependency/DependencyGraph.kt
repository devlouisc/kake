package dev.louisc.kake.dependency

data class DependencyGraph internal constructor(
    val rootNodes: List<DependencyNode>
)
