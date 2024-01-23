package com.cli.info;

import com.cli.api.Memory;
import com.cli.api.Networking;
import com.cli.api.OperatingSystem;
import com.cli.api.User;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.Map;

@Command(name = "info", description = "Get @|bold,red,blink operating system|@ information.",
        mixinStandardHelpOptions = true)
final public class InfoCommand implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(InfoCommand.class);
    @Option(names = {"-os"}, description = "Operating system general information.")
    boolean os;
    @Option(names = {"-mem"}, description = "Memory information.")
    boolean memory;
    @Option(names = {"-net"}, description = "Networking information.")
    boolean networking;
    @Option(names = {"-user"}, description = "User information.")
    boolean user;
    @Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;
    @Inject
    Memory mem;
    @Inject
    Networking net;
    @Inject
    OperatingSystem oss;
    @Inject
    User use;

    @Override
    public void run() {

        try {
            if (os) {
                Map<String, String> osInfo = oss.osGeneralInfo();

                for (Map.Entry<String, String> entry : osInfo.entrySet()) {
                    System.out.printf("%-20s: %s%n", entry.getKey(),
                            Ansi.AUTO.string("@|bold,fg(green) " + entry.getValue() + "|@"));
                }

            } else if (networking) {
                Map<String, String> netInfo = net.osNetworkingInfo();

                for (Map.Entry<String, String> entry : netInfo.entrySet()) {
                    System.out.printf("%-20s: %s%n", entry.getKey(),
                            Ansi.AUTO.string("@|bold,fg(yellow) " + entry.getValue() + "|@"));
                }
            } else if (memory) {
                Map<String, String> memInfo = mem.osMemoryInfo();

                for (Map.Entry<String, String> entry : memInfo.entrySet()) {
                    System.out.printf("%-30s: %s%n", entry.getKey(),
                            Ansi.AUTO.string("@|bold,fg(red) " + entry.getValue() + "|@"));
                }
            } else if (user) {
                Map<String, String> userInfo = use.osUser();

                for (Map.Entry<String, String> entry : userInfo.entrySet()) {
                    System.out.printf("%-14s: %s%n", entry.getKey(),
                            Ansi.AUTO.string("@|bold,fg(magenta) " + entry.getValue() + "|@"));
                }
            } else {
                System.out.println("No information selected. Use --help for usage information.");
            }

        } catch (IOException | RuntimeException | InterruptedException e) {
            logger.error("An exception occurred while processing the 'info' command", e);
        }

        if (verbose) {
            System.out.println("Osys-Cli");
        }
    }
}
