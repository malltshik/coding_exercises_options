package ru.malltshik.codingexercisesoptions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malltshik.codingexercisesoptions.configurations.ForeignServerConfiguration;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;
import ru.malltshik.codingexercisesoptions.services.AttributeService;
import ru.malltshik.codingexercisesoptions.services.AttributeService.Attribute;
import ru.malltshik.codingexercisesoptions.services.AttributeService.City;

import java.util.List;

/**
 * Controller for attributes from foreign server
 * <p>
 * Service details {@link AttributeService}
 * Configuration foreign server {@link ForeignServerConfiguration}
 * Application foreign server properties {@link AttributeServerProperties}
 */
@RestController
@RequestMapping("/api/attributes")
public class AttributesController {

    private final AttributeService attributeService;

    @Autowired
    public AttributesController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/gender")
    public List<Attribute> getGender() {
        return attributeService.getGenders();
    }

    @GetMapping("/ethnicity")
    public List<Attribute> getEthnicity() {
        return attributeService.getEthnicity();
    }

    @GetMapping("/religion")
    public List<Attribute> getReligion() {
        return attributeService.getReligion();
    }

    @GetMapping("/figure")
    public List<Attribute> getFigure() {
        return attributeService.getFigure();
    }

    @GetMapping("/maritalStatus")
    public List<Attribute> getMaritalStatus() {
        return attributeService.getMaritalStatus();
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return attributeService.getCities();
    }


}
