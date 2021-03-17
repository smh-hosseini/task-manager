package org.iptiq.assignment.service;


import org.iptiq.assignment.config.Configs;
import org.iptiq.assignment.config.QueueConfigs;
import org.iptiq.assignment.domain.TaskManagerType;

import javax.enterprise.context.ApplicationScoped;

import static org.iptiq.assignment.domain.TaskManagerType.*;

@ApplicationScoped
public class TaskManagerFactory {

    public TaskManager createTaskManager(TaskManagerType type) {

         if (type.equals(PRIORITY)){
             return new PriorityBasedTaskManager();
         } else if (type.equals(FIFO)){
             return new FIFOTaskManager();
         } else {
             return new DefaultTaskManger();
         }

    }

}
