package org.iptiq.assignment.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "taskmanager")
public class QueueConfigs {

    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
