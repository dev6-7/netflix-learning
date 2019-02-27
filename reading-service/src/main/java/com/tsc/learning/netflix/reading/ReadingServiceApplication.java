package com.tsc.learning.netflix.reading;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tsc.learning.netflix.reading.config.RibbonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@RestController
@RibbonClient(
        name = "task-service",
        configuration = RibbonConfig.class)
public class ReadingServiceApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @RequestMapping("/taskList")
    @HystrixCommand(fallbackMethod = "defaultTask")
    public String taskList() {
        URI uri = URI.create("http://task-service/taskList");
        return restTemplate.getForObject(uri, String.class);
    }

    public String defaultTask() {
        return "Live happy";
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingServiceApplication.class, args);
    }
}
