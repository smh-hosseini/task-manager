package org.iptiq.assignment.service;

import org.iptiq.assignment.config.Configs;
import org.iptiq.assignment.domain.Process;
import org.iptiq.assignment.domain.TaskManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.iptiq.assignment.domain.Priority.*;

class FIFOTaskManagerTest {

    private TaskManager taskManger;
    private Configs configs;

    @BeforeEach
    void beforeAll() {
        taskManger = new TaskManagerFactory().createTaskManager(TaskManagerType.FIFO);
    }
    @AfterEach
    void killAllTasks() {
        taskManger.killAll();
    }

    @Test
    void addProcess() {
        configs = new Configs(2);
        Process process = new Process("1", HIGH);
        taskManger.addProcess(process);
        Process process2 = new Process("2", LOW);
        taskManger.addProcess(process2);
        Process process3 = new Process("3", MEDIUM);
        taskManger.addProcess(process3);
        Assertions.assertEquals(2, taskManger.list().size());
        Assertions.assertEquals(List.of(LOW, MEDIUM), taskManger.list().stream().map(process1 -> process1.getPriority()).collect(Collectors.toList()));
    }

}