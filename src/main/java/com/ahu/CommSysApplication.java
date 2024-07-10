package com.ahu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CommSysApplication {

    public static void main(String[] args) {

        SpringApplication.run(CommSysApplication.class, args);
    }

}
