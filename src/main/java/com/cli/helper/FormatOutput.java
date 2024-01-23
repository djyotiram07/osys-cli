package com.cli.helper;

import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class FormatOutput {
    public String formatBytes(long bytes) {
        if (bytes < 0) {
            throw new IllegalArgumentException("Byte size cannot be negative.");
        }

        double kilobytes = bytes / 1024.0;
        double megabytes = kilobytes / 1024.0;
        double gigabytes = megabytes / 1024.0;

        String unit = "KB";
        double value;

        if (gigabytes >= 1.0) {
            value = gigabytes;
            unit = "GB";
        } else if (megabytes >= 1.0) {
            value = megabytes;
            unit = "MB";
        } else {
            value = kilobytes;
        }

        return String.format("%.1f %s", value, unit);
    }

    public String uptimeSystem() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("uptime");
        process.waitFor();

        StringBuilder output = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
            output.append(new String(buffer, 0, bytesRead));
        }

        return output.toString();
    }
}
