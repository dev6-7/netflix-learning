package com.tsc.learning.netflix.reading;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@RestController
public class ReadingServiceApplication {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/taskList")
    @HystrixCommand(fallbackMethod = "defaultTask")
    public String taskList() {
        URI uri = URI.create("http://localhost:8082/taskList");
        return restTemplate.getForObject(uri, String.class);
    }

    public String defaultTask() {
        return "Live happy";
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingServiceApplication.class, args);
    }
}
