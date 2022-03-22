package com.codedifferently.taskbunnyapi.domain.taskbunny.services;

import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyCreationException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyNotFoundException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.models.TaskBunny;

import java.util.List;

public interface TaskBunnyService {
    TaskBunny create(TaskBunny taskBunny) throws TaskBunnyCreationException;
    TaskBunny getTaskBunnyById(Long id) throws TaskBunnyNotFoundException;
    List<TaskBunny> getAllTaskBunnies();
    TaskBunny updateTaskBunny(TaskBunny widget) throws TaskBunnyNotFoundException;
    void deleteTaskBunny(Long id) throws TaskBunnyNotFoundException;
}
