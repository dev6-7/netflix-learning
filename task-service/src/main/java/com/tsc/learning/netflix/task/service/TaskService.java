package com.tsc.learning.netflix.task.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class TaskService {

    private final RestTemplate restTemplate;

    public TaskService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String taskList() {
        URI uri = URI.create("http://localhost:8082/taskList");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Task do nothing";
    }

}
