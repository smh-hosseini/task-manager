package org.iptiq.assignment.service;

import org.iptiq.assignment.config.Configs;
import org.iptiq.assignment.domain.Priority;
import org.iptiq.assignment.domain.Process;
import org.iptiq.assignment.exceptions.ProcessNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public interface TaskManager {

    String addProcess(Process process);

    default List<Process> list() {
        return Configs.processes.stream().collect(Collectors.toList());
    }

    default void kill(String pid) {
        Configs.processes.stream().filter(process -> process.getPid().equals(pid)).findFirst()
                .orElseThrow(() -> new ProcessNotFoundException(String.format("Couldn't found process with pid (%s)", pid)))
                .kill();
    }

    default void killAll() {
        Configs.processes.stream().forEach(process-> {
            process.kill();
        });
    }

    default void killAll(Priority priority) {
        Configs.processes.stream().filter(process -> process.getPriority().equals(priority)).forEach(process-> {
            process.kill();
        });
    }
}
