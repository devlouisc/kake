package dev.louisc.kake.dependency

import dev.louisc.kake.dependency.maven.MavenModelGetter

data class DependencyGraph(
    val rootNodes: List<DependencyNode>
)

class DependencyGraphFactory(_mavenModelGetter: MavenModelGetter) {

    private val mavenModelGetter = _mavenModelGetter

    fun make(dependencyIds: List<DependencyIdentifier>): DependencyGraph {
        return DependencyGraph(rootNodes = dependencyIds.map { makeNodeRecursively(it) })
    }

    private fun makeNodeRecursively(dependencyId: DependencyIdentifier): DependencyNode {
        val model = mavenModelGetter.get(dependencyId)
        val children = model.dependencies.map {
            makeNodeRecursively(dependencyId = DependencyIdentifier(it.groupId, it.artifactId, it.version))
        }
        return DependencyNode(dependencyId, model, children)
    }
}
