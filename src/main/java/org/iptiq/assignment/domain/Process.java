package org.iptiq.assignment.domain;


import org.iptiq.assignment.config.Configs;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Process implements Runnable, Comparable<Process> {

    private AtomicBoolean exit = new AtomicBoolean(false);
    private final String pid;
    private final Priority priority;
    private final Instant created;
    private Thread worker;

    public Process(String pid, Priority priority) {
        this.pid = pid;
        this.priority = priority;
        this.created = Instant.now();
    }

    public final String getPid() {
        return pid;
    }

    public Priority getPriority() {
        return priority;
    }

    public Instant getCreated() {
        return created;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
        Configs.processes.add(this);
    }

    public void run() {
        System.out.println(String.format("Process with PID = %s and Priority = %s", pid, priority));
        while(!exit.get()) {
            try {
                System.out.println(String.format("Process with PID = %s is still running", pid));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }

        }
        System.out.println(String.format("Exit process with PID = %s and Priority = %s", pid, priority));
    }
    public void kill() {
        Configs.processes.remove(this);
        exit.set(true);
        worker.interrupt();
    }

    @Override
    public int compareTo(Process other) {
        final int p = getPriority().compareTo(other.priority);
        if (p != 0) return p;
        return created.compareTo(other.created);
    }
}
