package org.iptiq.assignment.service;

import org.iptiq.assignment.config.Configs;
import org.iptiq.assignment.domain.Process;


public class DefaultTaskManger implements TaskManager {

    public synchronized String addProcess(Process process) {
        if (Configs.processes.remainingCapacity() > 0) {
            process.start();
        }
        return process.getPid();
    }

}
