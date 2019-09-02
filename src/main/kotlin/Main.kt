import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options

fun main(args: Array<String>) {
    val options = Options()
            .addOption(Option.builder()
                    .argName("install")
                    .desc("Install project dependencies")
                    .build())

    val cli = DefaultParser().parse(options, args)

    if (cli.hasOption("install")) {
        println("Command 'install' encountered!")
    }
}
