package dev.louisc.kake.project

import dev.louisc.kake.NotFoundException
import dev.louisc.kake.projectJsonFileName
import java.nio.file.Path

/**
 * Locates a project.json file by starting at the specified path and progressively searching up the directory hierarchy.
 */
fun locateProjectJson(startPath: Path): Path {
    var currentPath = startPath.toAbsolutePath()
    while (currentPath.toString().isNotEmpty()) {
        val projectJsonFilePath = currentPath.resolve(projectJsonFileName)
        if (projectJsonFilePath.toFile().exists()) {
            return projectJsonFilePath
        }
        currentPath = currentPath.parent
    }
    throw NotFoundException("Unable to locate $projectJsonFileName by starting search at ${startPath.toAbsolutePath()}.")
}
