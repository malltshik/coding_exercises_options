package ru.malltshik.codingexercisesoptions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration;
import ru.malltshik.codingexercisesoptions.configurations.ControllerAdviceConfiguration.ResponseError;
import ru.malltshik.codingexercisesoptions.controllers.models.TestProfile;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    private final TestProfile profile = TestProfile.builder()
            .displayName("Display Name")
            .realName("Real name")
            .birthday(new Date())
            .gender("Male")
            .height(1923)
            .maritalStatus("Never Married")
            .location("St.Petersburt (59°57'N, 30°18'E)")
            .build();

    private TestProfile createOne() throws java.io.IOException {
        ResponseEntity<String> response = template.postForEntity(URL(), profile, String.class);
        assertTrue(response.hasBody());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        TestProfile profile = MAPPER.readValue(response.getBody(), TestProfile.class);
        this.profile.setBirthday(profile.getBirthday());
        this.profile.setId(profile.getId());
        return profile;
    }

    @Test
    public void test00_notFoundProfileTest() throws IOException {
        ResponseEntity<String> response = template.getForEntity(URL(-42L), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertTrue(response.hasBody());
        ResponseError responseError = MAPPER.readValue(response.getBody(), ResponseError.class);
        assertNotNull(responseError);
        assertEquals(responseError.getType(), "NotFoundException");
        assertEquals(responseError.getMessage(), "Profile with id -42 not found");
    }

    @Test
    public void test01_saveProfile() throws Exception {
        TestProfile responseProfile = createOne();
        assertNotNull(responseProfile.getId());
        assertNull(responseProfile.getRealName());
        assertNull(responseProfile.getMaritalStatus());
        assertNull(responseProfile.getOccupation());
        assertEquals(responseProfile.getDisplayName(), profile.getDisplayName());
        assertEquals(responseProfile.getBirthday(), profile.getBirthday());
        assertEquals(responseProfile.getGender(), profile.getGender());
        assertEquals(responseProfile.getHeight(), profile.getHeight());
        assertEquals(responseProfile.getLocation(), profile.getLocation());
    }

    @Test
    public void test02_updateProfile() throws Exception {
        createOne();
        profile.setDisplayName("Name two");
        ResponseEntity<String> response = template.exchange(URL(profile.getId()), HttpMethod.PUT,
                new HttpEntity<>(profile), String.class);
        assertTrue(response.hasBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        TestProfile responseProfile = MAPPER.readValue(response.getBody(), TestProfile.class);
        assertNull(responseProfile.getRealName());
        assertNull(responseProfile.getMaritalStatus());
        assertNull(responseProfile.getOccupation());
        assertEquals(responseProfile.getDisplayName(), profile.getDisplayName());
        assertEquals(responseProfile.getBirthday(), profile.getBirthday());
        assertEquals(responseProfile.getGender(), profile.getGender());
        assertEquals(responseProfile.getHeight(), profile.getHeight());
        assertEquals(responseProfile.getLocation(), profile.getLocation());
    }

    @Test
    public void test04_getOne() throws Exception {
        createOne();
        ResponseEntity<String> one = template.getForEntity(URL() + profile.getId(), String.class);
        assertTrue(one.hasBody());
        assertEquals(one.getStatusCode(), HttpStatus.OK);
        TestProfile responseProfile = MAPPER.readValue(one.getBody(), TestProfile.class);
        assertNull(responseProfile.getRealName());
        assertNull(responseProfile.getMaritalStatus());
        assertNull(responseProfile.getOccupation());
        assertEquals(responseProfile.getDisplayName(), profile.getDisplayName());
        assertEquals(responseProfile.getBirthday(), profile.getBirthday());
        assertEquals(responseProfile.getGender(), profile.getGender());
        assertEquals(responseProfile.getHeight(), profile.getHeight());
        assertEquals(responseProfile.getLocation(), profile.getLocation());
    }

    @Test
    public void test03_getAll() throws Exception {
        createOne();
        ResponseEntity<String> profiles = template.getForEntity(URL(), String.class);
        assertTrue(profiles.hasBody());
        assertEquals(profiles.getStatusCode(), HttpStatus.OK);
        TestProfile[] testProfiles = MAPPER.readValue(profiles.getBody(), TestProfile[].class);
        assertTrue(testProfiles.length > 0);
    }

    @Test
    public void test05_deleteProfile() throws Exception {
        createOne();
        ResponseEntity<String> response = template.exchange(URL() + profile.getId(), HttpMethod.DELETE,
                null, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private String URL() {
        return "http://localhost:" + port + "/api/profile/";
    }

    private String URL(Long id) {
        return URL() + id;
    }

}