package com.example.egzaminrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan( basePackages = {"domain"} )
public class EgzaminRestApplication {
    public static void main(String... args) {
        SpringApplication.run(EgzaminRestApplication.class, args);
    }
}
