package ru.malltshik.codingexercisesoptions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
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
     * not editable, only on signup. In centimeter.
     */
    private int height;

    /**
     * single selection
     */
    private String figure;

    /**
     * single selection
     */
    private String maritalStatus;

    /**
     * free text, up to 256 char
     */
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
