package ru.malltshik.codingexercisesoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;

/**
 * TODO add more logs
 * TODO add javadoc to each class
 */
@SpringBootApplication
@EnableConfigurationProperties(AttributeServerProperties.class)
public class CodingExercisesOptionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingExercisesOptionsApplication.class, args);
    }
}

