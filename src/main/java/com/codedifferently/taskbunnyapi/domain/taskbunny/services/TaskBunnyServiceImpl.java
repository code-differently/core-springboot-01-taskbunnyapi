package com.codedifferently.taskbunnyapi.domain.taskbunny.services;


import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyCreationException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyNotFoundException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.models.TaskBunny;
import com.codedifferently.taskbunnyapi.domain.taskbunny.repos.TaskBunnyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskBunnyServiceImpl implements TaskBunnyService{
    private static Logger logger = LoggerFactory.getLogger(TaskBunnyServiceImpl.class);
    private TaskBunnyRepo taskBunnyRepo;

    @Autowired
    public TaskBunnyServiceImpl(TaskBunnyRepo taskBunnyRepo){
        this.taskBunnyRepo = taskBunnyRepo;
    }


    @Override
    public TaskBunny create(TaskBunny taskBunny) throws TaskBunnyCreationException {
        logger.debug("Creating Task Bunny with email {}", taskBunny.getEmail());
        Optional<TaskBunny> taskBunnyOptional = taskBunnyRepo.findByEmail(taskBunny.getEmail());
        if(taskBunnyOptional.isPresent()) {
            logger.error("Task Bunny already exist with email {}", taskBunny.getEmail());
            throw new TaskBunnyCreationException("Task bunny exist");
        }
        return taskBunnyRepo.save(taskBunny);
    }

    @Override
    public TaskBunny getTaskBunnyById(Long id) throws TaskBunnyNotFoundException {
        logger.debug("Looking for Task bunny with id {}", id);
        Optional<TaskBunny> taskBunnyOptional = taskBunnyRepo.findById(id);
        if(taskBunnyOptional.isEmpty())
            throw new TaskBunnyNotFoundException("No task bunny with id");
        return taskBunnyOptional.get();
    }

    @Override
    public List<TaskBunny> getAllTaskBunnies() {
        return (List) taskBunnyRepo.findAll();
    }

    @Override
    public TaskBunny updateTaskBunny(TaskBunny taskbunny) throws TaskBunnyNotFoundException {
        Long id = taskbunny.getId();
        Optional<TaskBunny> taskBunnyExistOption = taskBunnyRepo.findById(id);
        if(taskBunnyExistOption.isEmpty())
            throw new TaskBunnyNotFoundException("No task bunny with id");
        return taskBunnyRepo.save(taskbunny);
    }

    @Override
    public void deleteTaskBunny(Long id) throws TaskBunnyNotFoundException {
        Optional<TaskBunny> taskBunnyExistOption = taskBunnyRepo.findById(id);
        if(taskBunnyExistOption.isEmpty())
            throw new TaskBunnyNotFoundException("No task bunny with id");
        TaskBunny taskBunnyToRemove = taskBunnyExistOption.get();
        taskBunnyRepo.delete(taskBunnyToRemove);
    }
}
