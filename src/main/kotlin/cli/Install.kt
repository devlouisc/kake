package dev.louisc.kake.cli

import dev.louisc.kake.project.locateProjectJson
import dev.louisc.kake.project.parseProjectJson
import java.nio.file.Path

fun install() {
    // Look for the project.json file in the current directory and work your way up until the root directory.
    // Throw exception if project.json cannot be found.
    val currentDirectory = Path.of(System.getProperty("user.dir"))
    val projectJsonPath = locateProjectJson(currentDirectory)
    val projectJsonText = ""
    val projectJson = parseProjectJson(projectJsonText)

    // Parse the project.json file and look for all dependencies.

    // Determine the repositories to download from.

    // Build the dependency tree.

    // Download the dependencies and place them in the local repository.
}
