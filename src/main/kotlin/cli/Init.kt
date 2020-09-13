package dev.louisc.kake.cli

import java.io.File

fun init() {
    val fileInputStream = {}::class.java.getResourceAsStream("/project-init.json")
    val fileOutputStream = File("project.json").outputStream()

    fileInputStream.use {
        fileOutputStream.use {
            fileInputStream.copyTo(fileOutputStream)
        }
    }
}
