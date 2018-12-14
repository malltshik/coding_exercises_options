package ru.malltshik.codingexercisesoptions.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public class Attribute {
        private String id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class City {
        private String lat;
        private String lon;
        private String city;
    }

}
