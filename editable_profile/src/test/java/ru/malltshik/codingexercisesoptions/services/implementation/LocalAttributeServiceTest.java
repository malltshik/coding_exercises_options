package ru.malltshik.codingexercisesoptions.services.implementation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.codingexercisesoptions.services.AttributeService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocalAttributeServiceTest {

    private static Process FOREIGN_SERVER_PROCESS;

    @BeforeClass
    public static void beforeClass() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "server/server.py");
        FOREIGN_SERVER_PROCESS = processBuilder.start();
    }

    @AfterClass
    public static void afterClass() {
        if (FOREIGN_SERVER_PROCESS != null && FOREIGN_SERVER_PROCESS.isAlive()) {
            FOREIGN_SERVER_PROCESS.destroy();
        }
    }

    @Autowired
    private AttributeService attributeService;

    @Test
    public void getGenders() throws Exception {
        List<AttributeService.Attribute> genders = attributeService.getGenders();
        assertFalse(genders.isEmpty());
        assertEquals(genders.size(), 3);
    }

    @Test
    public void getEthnicity() throws Exception {
        List<AttributeService.Attribute> ethnicity = attributeService.getEthnicity();
        assertFalse(ethnicity.isEmpty());
        assertEquals(ethnicity.size(), 11);
    }

    @Test
    public void getReligion() throws Exception {
        List<AttributeService.Attribute> religion = attributeService.getReligion();
        assertFalse(religion.isEmpty());
        assertEquals(religion.size(), 10);
    }

    @Test
    public void getFigure() throws Exception {
        List<AttributeService.Attribute> figure = attributeService.getFigure();
        assertFalse(figure.isEmpty());
        assertEquals(figure.size(), 5);
    }

    @Test
    public void getMaritalStatus() throws Exception {
        List<AttributeService.Attribute> getMaritalStatus = attributeService.getMaritalStatus();
        assertFalse(getMaritalStatus.isEmpty());
        assertEquals(getMaritalStatus.size(), 4);
    }

    @Test
    public void getCities() throws Exception {
        List<AttributeService.City> cities = attributeService.getCities();
        assertFalse(cities.isEmpty());
        assertEquals(cities.size(), 1096);
    }

}