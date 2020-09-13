package dev.louisc.kake.dependency

import java.util.stream.Collectors

class DependencyGraph private constructor(_rootNodes: List<DependencyNode>) {

    val rootNodes = _rootNodes

    companion object {
        private val dependencyStringRegex = Regex.fromLiteral("^([^:]*):([^:]*):([^:]*)$")

        fun build(dependencies: List<String>): DependencyGraph {
            val rootNodes = dependencies.stream()
                .map {
                    val matches = dependencyStringRegex.matchEntire(it)
                    checkNotNull(matches) { "Malformed dependency string: $it." }

                    val groupId = matches.groupValues[0]
                    val artifactId = matches.groupValues[1]
                    val version = matches.groupValues[2]

                    DependencyNode(groupId, artifactId, version)
                }
                .collect(Collectors.toList())

            return DependencyGraph(rootNodes)
        }
    }
}
