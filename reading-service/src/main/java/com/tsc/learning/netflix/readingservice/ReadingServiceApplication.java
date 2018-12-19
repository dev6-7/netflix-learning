package com.tsc.learning.netflix.readingservice;

import com.tsc.learning.netflix.readingservice.config.RibbonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableCircuitBreaker
@SpringBootApplication
@RestController
@RibbonClient(
        name = "book-service",
        configuration = RibbonConfig.class)
public class ReadingServiceApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Qualifier("restTemplate")
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/to-read")
    public String readingList() {
        URI uri = URI.create("http://book-service/recommended");

        return restTemplate.getForObject(uri, String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingServiceApplication.class, args);
    }
}
