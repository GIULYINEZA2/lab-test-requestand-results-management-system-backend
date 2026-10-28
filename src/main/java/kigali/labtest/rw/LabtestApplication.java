package kigali.labtest.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LabtestApplication {

    public static void main(String[] args) {
        // Explicitly printing a standard student-style startup trace message
        System.out.println("Starting Labtest Application initialization...");

        // Storing the application context into an explicit local variable
        // instead of invoking the run method completely inline
        ConfigurableApplicationContext context = SpringApplication.run(LabtestApplication.class, args);

        System.out.println("Labtest Application started successfully!");
    }
}
