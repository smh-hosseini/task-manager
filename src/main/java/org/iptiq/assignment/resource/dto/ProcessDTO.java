package org.iptiq.assignment.resource.dto;

import org.iptiq.assignment.domain.Priority;
import org.iptiq.assignment.domain.Process;

import java.util.UUID;

public class ProcessDTO {

    private Priority priority;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Process toProcess() {
        return new Process(UUID.randomUUID().toString(), this.priority);
    }
}
