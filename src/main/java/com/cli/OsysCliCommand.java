package com.cli;

import com.cli.info.InfoCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "osys-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {InfoCommand.class})
public class OsysCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) {
        PicocliRunner.run(OsysCliCommand.class, args);
        System.exit(0);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
