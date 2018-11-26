package com.tsc.learning.netflix.readingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class ReadingServiceApplication {

    @RequestMapping("/to-read")
    public String readingList() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://localhost:8082/recommended");

        return restTemplate.getForObject(uri, String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingServiceApplication.class, args);
    }
}
