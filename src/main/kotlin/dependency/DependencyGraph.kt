package dev.louisc.kake.dependency

import dev.louisc.kake.dependency.maven.MavenModelGetter
import java.util.stream.Collectors

data class DependencyGraph(val rootNodes: List<DependencyNode>)

class DependencyGraphFactory(_mavenModelGetter: MavenModelGetter) {

    private val mavenModelGetter = _mavenModelGetter

    fun make(dependencies: List<DependencyIdentifier>): DependencyGraph {
        val rootNodes = dependencies.stream()
            .map { DependencyNode(id = it, model = mavenModelGetter.get(it)) }
            .collect(Collectors.toList())
        return DependencyGraph(rootNodes)
    }
}
