package ru.malltshik.codingexercisesoptions.services.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.malltshik.codingexercisesoptions.properties.AttributeServerProperties;
import ru.malltshik.codingexercisesoptions.repositories.implementations.ProfileJDBCRepository;
import ru.malltshik.codingexercisesoptions.services.AttributeService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;

/**
 * This {@link AttributeService} implementation works next way:
 * After creating bean, there is attributes and locations loading process to the local cache fields.
 * Over {@link RestTemplate} {@link #loadAttributes()} and {@link #loadLocations()} methods get JSON data from
 * foreign server.
 * You have to define some {@link AttributeServerProperties}, otherwise application go down with
 * properties validation exception. Even more application can go down if foreign server will be unavailable.
 * Procedures loading attributes and locations are repeatable with {@link Scheduled} annotation. By default every day
 * in midnight.
 */
@Component
public class LocalAttributeService implements AttributeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LocalAttributeService.class);

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();
    private Map<String, List<Attribute>> attributeCache = new ConcurrentHashMap<>();
    private List<City> locationsCache = new ArrayList<>();
    private AttributeServerProperties properties;

    @Autowired
    public void setProperties(AttributeServerProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * *")
    private void postConstruct() {
        try {
            LOGGER.info("Foreign server attributes loading process start");
            loadAttributes();
            loadLocations();
            LOGGER.info("Foreign server attributes loading process end");
        } catch (ResourceAccessException e) {
            String message = "Unable load data from foreign server";
            LOGGER.error(message,e);
            throw new IllegalStateException(message);
        }
    }

    @Override
    public List<Attribute> getGenders() {
        return attributeCache.get("gender");
    }

    @Override
    public List<Attribute> getEthnicity() {
        return attributeCache.get("ethnicity");
    }

    @Override
    public List<Attribute> getReligion() {
        return attributeCache.get("religion");
    }

    @Override
    public List<Attribute> getFigure() {
        return attributeCache.get("figure");
    }

    @Override
    public List<Attribute> getMaritalStatus() {
        return attributeCache.get("maritalStatus");
    }

    @Override
    public List<City> getCities() {
        return locationsCache;
    }

    private void loadLocations() {
        LOGGER.info("Load attributes from {} endpoint in progress", properties.getLocations());
        ResponseEntity<LocationDto> entity = REST_TEMPLATE.getForEntity(
                properties.getUrl() + properties.getLocations(), LocationDto.class);
        if (entity.getStatusCode().is2xxSuccessful() && entity.hasBody()) {
            LocationDto body = entity.getBody();
            for (City city : body.cities) {
                if (!locationsCache.contains(city)) locationsCache.add(city);
            }
            LOGGER.info("Load attributes from {} endpoint done", properties.getLocations());
        } else {
            throw new IllegalStateException("Unable load locations from foreign server");
        }
    }

    private void loadAttributes() {
        LOGGER.info("Load attributes from {} endpoint in progress", properties.getAttributes());
        ResponseEntity<AttributeDto> entity = REST_TEMPLATE.getForEntity(
                properties.getUrl() + properties.getAttributes(), AttributeDto.class);
        if (entity.getStatusCode().is2xxSuccessful() && entity.hasBody()) {
            AttributeDto body = entity.getBody();
            attributeCache.put("gender", body.gender != null ? body.gender : emptyList());
            attributeCache.put("ethnicity", body.ethnicity != null ? body.ethnicity : emptyList());
            attributeCache.put("religion", body.religion != null ? body.religion : emptyList());
            attributeCache.put("figure", body.figure != null ? body.figure : emptyList());
            attributeCache.put("maritalStatus", body.maritalStatus != null ? body.maritalStatus : emptyList());
            LOGGER.info("Load attributes from {} endpoint done", properties.getAttributes());
        } else {
            throw new IllegalStateException("Unable load attributes from foreign server");
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class AttributeDto {
        List<Attribute> gender;
        List<Attribute> ethnicity;
        List<Attribute> religion;
        List<Attribute> figure;
        @JsonProperty("marital_status")
        List<Attribute> maritalStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class LocationDto {
        List<City> cities;
    }

}
