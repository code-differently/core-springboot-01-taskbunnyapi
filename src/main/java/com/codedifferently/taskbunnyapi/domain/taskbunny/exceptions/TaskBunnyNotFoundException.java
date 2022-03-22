package com.codedifferently.taskbunnyapi.domain.taskbunny.exceptions;

public class TaskBunnyNotFoundException extends Exception{
    public TaskBunnyNotFoundException(String msg){
        super(msg);
    }
}
