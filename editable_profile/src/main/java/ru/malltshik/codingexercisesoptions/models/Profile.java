package ru.malltshik.codingexercisesoptions.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * TODO add javax.validations for each field
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {

    /**
     * primary key
     */
    private Long id;

    /**
     * free text, up to 256 char
     */
    private String displayName;

    /**
     * free text, up to 256 char
     */
    @JsonProperty(access = WRITE_ONLY)
    private String realName;
    /**
     * upload component
     */
    private byte[] picture;
    /**
     * date
     */
    private Date birthday;
    /**
     * single selection
     */
    private String gender;
    /**
     * single selection
     */
    private String ethnicity;
    /**
     * single selection
     */
    private String religion;
    /**
     * not editable, only on signup. In millimeter.
     */
    private int height;

    /**
     * single selection
     */
    private String figure;

    /**
     * single selection
     */
    @JsonProperty(access = WRITE_ONLY)
    private String maritalStatus;

    /**
     * free text, up to 256 char
     */
    @JsonProperty(access = WRITE_ONLY)
    private String occupation;

    /**
     * free text, up to 5000 char
     */
    private String aboutMe;

    /**
     * text autocomplete (type city name, store lat/lon)
     */
    private String location;

}
