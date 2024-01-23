package com.cli.api;

import com.cli.helper.FormatOutput;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class Networking {

    private final FormatOutput formatOutput;

    @Inject
    public Networking(FormatOutput formatOutput) {
        this.formatOutput = formatOutput;
    }

    public Map<String, String> osNetworkingInfo() throws IOException, InterruptedException {
        InetAddress localHost = InetAddress.getLocalHost();
        Map<String, String> net = new HashMap<>();
        net.put("Host Name", localHost.getHostName());
        net.put("Host Address", localHost.getHostAddress());
        net.put("Canonical Host Name", localHost.getCanonicalHostName());
        net.put("System Uptime", formatOutput.uptimeSystem());
        return net;
    }
}
