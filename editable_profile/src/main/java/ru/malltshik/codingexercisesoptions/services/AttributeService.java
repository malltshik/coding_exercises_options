package ru.malltshik.codingexercisesoptions.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service which provide static attributes and locations from another resource.
 * Whole attributes has only two fields {@link Attribute#id} and {@link Attribute#name}
 * At the same time location has tree {@link City#lat}, {@link City#lon} and {@link City#city}
 * These dto classes could be used for selected fields of profile.
 */
@Service
public interface AttributeService {

    List<Attribute> getGenders();

    List<Attribute> getEthnicity();

    List<Attribute> getReligion();

    List<Attribute> getFigure();

    List<Attribute> getMaritalStatus();

    List<City> getCities();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Attribute {
        private String id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class City {
        private String lat;
        private String lon;
        private String city;
    }

}
