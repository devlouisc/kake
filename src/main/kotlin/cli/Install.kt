package dev.louisc.kake.cli

import dev.louisc.kake.project.locateProjectJson
import dev.louisc.kake.project.parseProjectJson
import java.nio.file.Files
import java.nio.file.Path

fun install() {
    val projectJsonPath = locateProjectJson(Path.of(System.getProperty("user.dir")))
    val projectJsonText = Files.readAllLines(projectJsonPath).joinToString()
    val projectJson = parseProjectJson(projectJsonText)

    // Look for all dependencies.

    // Determine the repositories to download from.

    // Build the dependency tree.

    // Download the dependencies and place them in the local repository.
}
