package kigali.labtest.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LabtestApplication {

    public static void main(String[] args) {

        System.out.println("Starting Labtest Application initialization...");

        ConfigurableApplicationContext context = SpringApplication.run(LabtestApplication.class, args);

        System.out.println("Labtest Application started successfully!");
    }
}
