package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Demo1Application {

    public static void main(String[] args) {
        System.out.println(new TestRO());
        SpringApplication.run(Demo1Application.class, args);
    }

}
