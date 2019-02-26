package com.tsc.learning.netflix.task.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @RequestMapping(value = "/taskList")
    public String taskList(){
        return "Create config, Create BE, Create FE";
    }
}
