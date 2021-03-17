package org.iptiq.assignment.service;

import org.iptiq.assignment.config.Configs;
import org.iptiq.assignment.domain.Process;
import org.iptiq.assignment.domain.TaskManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.iptiq.assignment.domain.Priority.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityBasedTaskManagerTest {

    private TaskManager taskManger;
    private Configs configs;

    @BeforeEach
    void beforeAll() {
        taskManger = new TaskManagerFactory().createTaskManager(TaskManagerType.PRIORITY);
    }

    @AfterEach
    void killAllTasks() {
        taskManger.killAll();
    }

    @Test
    void addProcess() {
        configs = new Configs(3);
        Process process = new Process("1", HIGH);
        taskManger.addProcess(process);
        Process process2 = new Process("2", LOW);
        taskManger.addProcess(process2);
        Process process3 = new Process("3", LOW);
        taskManger.addProcess(process3);
        Process process4 = new Process("4", MEDIUM);
        taskManger.addProcess(process4);
        assertEquals(3, taskManger.list().size());
        assertEquals(List.of("1", "3", "4"), taskManger.list().stream().map(process1 -> process1.getPid()).collect(Collectors.toList()));
    }
}