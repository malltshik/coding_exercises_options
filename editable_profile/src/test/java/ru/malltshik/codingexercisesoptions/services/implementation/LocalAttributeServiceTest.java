package ru.malltshik.codingexercisesoptions.services.implementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.codingexercisesoptions.services.AttributeService;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocalAttributeServiceTest {

    @Autowired
    private AttributeService attributeService;

    @Test
    public void getGenders() throws Exception {
        List<AttributeService.Attribute> genders = attributeService.getGenders();
        assertFalse(genders.isEmpty());
    }

    @Test
    public void getEthnicity() throws Exception {
    }

    @Test
    public void getReligion() throws Exception {
    }

    @Test
    public void getFigure() throws Exception {
    }

    @Test
    public void getMaritalStatus() throws Exception {
    }

    @Test
    public void getCities() throws Exception {
    }

    @Test
    public void setProperties() throws Exception {
    }

}