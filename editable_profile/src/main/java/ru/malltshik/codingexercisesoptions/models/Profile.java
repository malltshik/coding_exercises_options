package ru.malltshik.codingexercisesoptions.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.malltshik.codingexercisesoptions.repositories.ProfileRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Profile entity class used to exchange with users through REST API.
 * Also used in {@link ProfileRepository} for storing to database and reading from it.
 * Also used in validation process with whole {@link javax.validation } annotations.
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
    @NotEmpty(message = "Display name is required field")
    @Length(max = 256, message = "Maximum length of display name should be less then 256 chars")
    private String displayName;

    /**
     * free text, up to 256 char
     */
    @JsonProperty(access = WRITE_ONLY)
    @NotEmpty(message = "Real name is required field")
    @Length(max = 256, message = "Maximum length of display name should be less then 256 chars")
    private String realName;
    /**
     * upload component
     */
    private byte[] picture;
    /**
     * date
     */
    @NotNull(message = "Birthday is required field")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date birthday;
    /**
     * single selection
     */
    @NotNull(message = "Gender is required field")
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
    private Integer height;

    /**
     * single selection
     */
    private String figure;

    /**
     * single selection
     */
    @JsonProperty(access = WRITE_ONLY)
    @NotNull(message = "Marital status is required field")
    private String maritalStatus;

    /**
     * free text, up to 256 char
     */
    @JsonProperty(access = WRITE_ONLY)
    @Length(max = 256, message = "Maximum length of occupation should be less then 256 chars")
    private String occupation;

    /**
     * free text, up to 5000 char
     */
    @Length(max = 5000, message = "Maximum length about information should be less then 5000 chars")
    private String aboutMe;

    /**
     * text autocomplete (type city name, store lat/lon)
     */
    @NotNull(message = "Location is required field")
    private String location;


    @JsonGetter("picture")
    public String getPictureBase64() {
        return this.picture != null ? Base64.getEncoder().encodeToString(this.picture) : null;
    }

    @JsonSetter("picture")
    public void setPictureBase64(String base64) {
        if(base64 != null) this.picture = Base64.getDecoder().decode(base64);
    }

}
