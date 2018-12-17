package ru.malltshik.codingexercisesoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;

/**
 * Spring boot application main class.
 *
 * TODO add more logs
 */
@SpringBootApplication
@EnableConfigurationProperties(AttributeServerProperties.class)
public class CodingExercisesOptionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingExercisesOptionsApplication.class, args);
    }
}

