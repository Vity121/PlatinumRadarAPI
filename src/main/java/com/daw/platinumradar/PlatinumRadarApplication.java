package com.daw.platinumradar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlatinumRadarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatinumRadarApplication.class, args);
    }

}
