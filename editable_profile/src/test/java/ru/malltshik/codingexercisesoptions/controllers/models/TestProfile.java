package ru.malltshik.codingexercisesoptions.controllers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestProfile {
    private Long id;
    private String displayName;
    private String realName;
    private byte[] picture;
    private Date birthday;
    private String gender;
    private String ethnicity;
    private String religion;
    private Integer height;
    private String figure;
    private String maritalStatus;
    private String occupation;
    private String aboutMe;
    private String location;
}
