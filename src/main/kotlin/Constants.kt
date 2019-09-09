package dev.louisc.kake

import java.nio.file.Path
import java.nio.file.Paths

val baseDirectory: Path = Paths.get(System.getProperty("user.home"), ".m2")
val localRepositoryDirectory = baseDirectory.resolve("repository")
