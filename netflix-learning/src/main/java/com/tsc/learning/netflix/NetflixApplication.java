package com.tsc.learning.netflix;

import com.tsc.learning.netflix.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class NetflixApplication {

    @Autowired
    private BookService bookService;

    @RequestMapping("/to-read")
    public String toRead() {
        return bookService.readingList();
    }

    public static void main(String[] args) {
        SpringApplication.run(NetflixApplication.class, args);
    }
}
