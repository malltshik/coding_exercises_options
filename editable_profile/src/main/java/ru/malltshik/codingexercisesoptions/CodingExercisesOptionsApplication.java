package ru.malltshik.codingexercisesoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;

@SpringBootApplication
@EnableConfigurationProperties(AttributeServerProperties.class)
public class CodingExercisesOptionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodingExercisesOptionsApplication.class, args);
	}
}

