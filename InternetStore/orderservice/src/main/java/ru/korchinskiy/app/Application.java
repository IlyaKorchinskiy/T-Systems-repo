package ru.korchinskiy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.korchinskiy")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
