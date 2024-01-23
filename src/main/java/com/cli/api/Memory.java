package com.cli.api;

import com.cli.helper.FormatOutput;
import com.sun.management.OperatingSystemMXBean;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class Memory {

    @Inject
    private final FormatOutput formatOutput;

    private final long totalMemorySize;
    private final long totalFreeMemorySize;
    private final long totalSwapSpaceSize;
    private final long totalFreeSwapSpaceSize;
    private final long usedMemorySize;
    private final long usedSwapSpaceSize;
    private final long committedVirtualMemorySize;
    private final double getCpuLoad;

    public Memory(FormatOutput formatOutput) {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        this.formatOutput = formatOutput;
        this.totalMemorySize = os.getTotalMemorySize();
        this.totalFreeMemorySize = os.getFreeMemorySize();
        this.totalSwapSpaceSize = os.getTotalSwapSpaceSize();
        this.totalFreeSwapSpaceSize = os.getFreeSwapSpaceSize();
        this.usedMemorySize = this.totalMemorySize - this.totalFreeMemorySize;
        this.usedSwapSpaceSize = this.totalSwapSpaceSize - this.totalFreeSwapSpaceSize;
        this.committedVirtualMemorySize = os.getCommittedVirtualMemorySize();
        this.getCpuLoad = os.getCpuLoad();
    }

    public Map<String, String> osMemoryInfo() {
        Map<String, String> mem = new HashMap<>();
        mem.put("Total Memory Size", formatOutput.formatBytes(this.totalMemorySize));
        mem.put("Free Memory Size", formatOutput.formatBytes(this.totalFreeMemorySize));
        mem.put("Used Memory Size", formatOutput.formatBytes(this.usedMemorySize));
        mem.put("Total Swap Space Size", formatOutput.formatBytes(this.totalSwapSpaceSize));
        mem.put("Total Free Swap Space Size", formatOutput.formatBytes(this.totalFreeSwapSpaceSize));
        mem.put("Used Swap Space Size", formatOutput.formatBytes(this.usedSwapSpaceSize));
        mem.put("Committed Virtual Memory Size", formatOutput.formatBytes(this.committedVirtualMemorySize));
        mem.put("Cpu Load", String.valueOf(this.getCpuLoad));
        return mem;
    }
}
