package dev.louisc.kake.dependency.maven

import dev.louisc.kake.dependency.DependencyIdentifier
import org.apache.maven.model.Model

interface MavenModelGetter {
    fun get(dependencyId: DependencyIdentifier): Model
}
