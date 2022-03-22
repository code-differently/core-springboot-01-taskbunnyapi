package com.codedifferently.taskbunnyapi.domain.taskbunny.controllers;

import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyCreationException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions.TaskBunnyNotFoundException;
import com.codedifferently.taskbunnyapi.domain.taskbunny.models.TaskBunny;
import com.codedifferently.taskbunnyapi.domain.taskbunny.services.TaskBunnyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskbunnies")
public class TaskBunnyController {
    private static Logger logger = LoggerFactory.getLogger(TaskBunnyController.class);
    private TaskBunnyService taskBunnyService;

    @Autowired
    public TaskBunnyController(TaskBunnyService taskBunnyService){
        this.taskBunnyService = taskBunnyService;
    }

    @PostMapping("")
    public ResponseEntity<TaskBunny> create(@RequestBody TaskBunny taskBunny){
        try {
            taskBunny = taskBunnyService.create(taskBunny);
            return new ResponseEntity<>(taskBunny, HttpStatus.CREATED);
        }catch (TaskBunnyCreationException exception){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<TaskBunny>> getAll() {
        List<TaskBunny> taskBunnies = taskBunnyService.getAllTaskBunnies();
        return new ResponseEntity<>(taskBunnies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            TaskBunny taskBunny = taskBunnyService.getTaskBunnyById(id);
            return new ResponseEntity<>(taskBunny, HttpStatus.OK);
        }catch (TaskBunnyNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody TaskBunny taskBunny){
        try {
            taskBunny = taskBunnyService.updateTaskBunny(taskBunny);
            return new ResponseEntity<>(taskBunny, HttpStatus.OK);
        }catch (TaskBunnyNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            taskBunnyService.deleteTaskBunny(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (TaskBunnyNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
