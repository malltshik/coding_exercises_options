package ru.malltshik.codingexercisesoptions.controllers;

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
import ru.malltshik.codingexercisesoptions.services.AttributeService;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AttributesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getGender() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/gender", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getEthnicity() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/ethnicity", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getReligion() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/religion", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getFigure() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/figure", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getMaritalStatus() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/maritalStatus", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getCities() throws Exception {
        ResponseEntity<String> resp = template.getForEntity(URL() + "/cities", String.class);
        assertTrue(resp.hasBody());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    private String URL() {
        return "http://localhost:" + port + "/api/attributes/";
    }
}