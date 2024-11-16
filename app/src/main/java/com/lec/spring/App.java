package com.lec.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public String getGreeting() {
        return "CustomApp identifier is test";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
