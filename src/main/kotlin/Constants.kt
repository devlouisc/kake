package dev.louisc.kake

import java.nio.file.Path

const val projectJsonFileName = "project.json"

val kakeDirectory = Path.of(System.getProperty("user.home"), ".kake")!!
val mavenDirectory = Path.of(System.getProperty("user.home"), ".m2")!!
val localRepositoryDirectory = mavenDirectory.resolve("repository")
