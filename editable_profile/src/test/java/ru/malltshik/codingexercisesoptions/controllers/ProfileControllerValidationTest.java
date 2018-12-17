package ru.malltshik.codingexercisesoptions.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration;
import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration.ResponseValidationError;
import ru.malltshik.codingexercisesoptions.controllers.models.TestProfile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProfileControllerValidationTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRequiredFieldOnCreate() throws IOException {
        ResponseEntity<String> response = template.postForEntity(URL(), new TestProfile(), String.class);
        assertTrue(response.hasBody());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        ResponseValidationError resp = MAPPER.readValue(response.getBody(), ResponseValidationError.class);
        assertNotNull(resp);
        assertEquals(resp.getType(), "ValidationModelException");
        Map<String, Set<String>> errors = resp.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.get("displayName").contains("Display name is required field"));
        assertTrue(errors.get("realName").contains("Real name is required field"));
        assertTrue(errors.get("birthday").contains("Birthday is required field"));
        assertTrue(errors.get("gender").contains("Gender is required field"));
        assertTrue(errors.get("maritalStatus").contains("Marital status is required field"));
        assertTrue(errors.get("location").contains("Location is required field"));
    }

    @Test
    public void lengthFieldValidationOnCreateTest() throws IOException {
        TestProfile profile = TestProfile.builder()
                .displayName(randomAlphabetic(257))
                .realName(randomAlphabetic(257))
                .gender("Male")
                .birthday(new Date())
                .maritalStatus("Never again")
                .location("St.Petersburt (59°57'N, 30°18'E)")
                .occupation(randomAlphabetic(257))
                .aboutMe(randomAlphabetic(5001))
                .build();
        ResponseEntity<String> response = template.postForEntity(URL(), profile, String.class);
        assertTrue(response.hasBody());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        ResponseValidationError resp = MAPPER.readValue(response.getBody(), ResponseValidationError.class);
        assertNotNull(resp);
        assertEquals(resp.getType(), "ValidationModelException");
        Map<String, Set<String>> errors = resp.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.get("displayName").contains("Maximum length of display name should be less then 256 chars"));
        assertTrue(errors.get("realName").contains("Maximum length of display name should be less then 256 chars"));
        assertTrue(errors.get("occupation").contains("Maximum length of occupation should be less then 256 chars"));
        assertTrue(errors.get("aboutMe").contains("Maximum length about information should be less then 5000 chars"));
    }

    private String URL() {
        return "http://localhost:" + port + "/api/profile/";
    }

}