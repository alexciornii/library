package md.esempla.library.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "md.esempla.library")
public class RestApp {

    public static void main(String[] args) {
        SpringApplication.run(RestApp.class, args);
    }
}
