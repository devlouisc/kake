package dev.louisc.kake

import dev.louisc.kake.cli.init
import dev.louisc.kake.cli.install
import dev.louisc.kake.cli.list
import dev.louisc.kake.cli.run
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options

fun main(args: Array<String>) {
    val options = Options()
        .addOption(
            Option.builder("init")
                .desc("Initialize the project with a project.json file")
                .build()
        )
        .addOption(
            Option.builder("install")
                .desc("Install project dependencies")
                .build()
        )
        .addOption(
            Option.builder("list")
                .desc("List all tasks")
                .build()
        )
        .addOption(
            Option.builder("run")
                .desc("Run specified tasks")
                .build()
        )

    val cli = DefaultParser().parse(options, args)
    when {
        cli.hasOption("init") -> init()
        cli.hasOption("install") -> install()
        cli.hasOption("list") -> list()
        cli.hasOption("run") -> run(cli.argList)
        else -> HelpFormatter().printHelp("kake", options)
    }
}
