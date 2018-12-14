package ru.malltshik.codingexercisesoptions.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;
import ru.malltshik.codingexercisesoptions.services.AttributeService;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class LocalAttributeService implements AttributeService {

    private AttributeServerProperties properties;
    private RestTemplate template = new RestTemplate();
    private final ParameterizedTypeReference<HashMap<String, Attribute>> ATTRIBUTE_TYPE =
            new ParameterizedTypeReference<HashMap<String, Attribute>>() {};

    @Override
    public List<Attribute> getGenders() {
        template.getForEntity(properties.getUrl() + properties.getAttributes(), ATTRIBUTE_TYPE, new String[]{});
        return null;
    }

    @Override
    public List<Attribute> getEthnicity() {
        return null;
    }

    @Override
    public List<Attribute> getReligion() {
        return null;
    }

    @Override
    public List<Attribute> getFigure() {
        return null;
    }

    @Override
    public List<Attribute> getMaritalStatus() {
        return null;
    }

    @Override
    public List<City> getCities() {
        return null;
    }

    @Autowired
    public void setProperties(AttributeServerProperties properties) {
        this.properties = properties;
    }

}
