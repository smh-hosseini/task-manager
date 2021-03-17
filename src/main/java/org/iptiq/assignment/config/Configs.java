package org.iptiq.assignment.config;

import org.iptiq.assignment.domain.Process;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Configs {

    public static BlockingDeque<Process> processes;
    public Configs(int maxCapacity) {
        processes = new LinkedBlockingDeque<>(maxCapacity);
    }
}

