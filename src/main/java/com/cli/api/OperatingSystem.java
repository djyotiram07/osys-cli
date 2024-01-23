package com.cli.api;

import jakarta.inject.Singleton;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class OperatingSystem {

    private final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Map<String, String> osGeneralInfo() {
        Map<String, String> os = new HashMap<>();
        os.put("Os Name", osBean.getName());
        os.put("Os Version", osBean.getVersion());
        os.put("Os Architecture", osBean.getArch());
        os.put("Os SysLoadAverage", String.valueOf(osBean.getSystemLoadAverage()));
        os.put("Os Available Processor", String.valueOf(osBean.getAvailableProcessors()));
        os.put("Os Screen Resolution", String.format("%fx%f", screenSize.getWidth(), screenSize.getHeight()));
        return os;
    }
}
