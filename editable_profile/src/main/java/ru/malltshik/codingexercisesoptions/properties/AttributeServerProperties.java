package ru.malltshik.codingexercisesoptions.properties;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Validated
@ConfigurationProperties(prefix = "attrserver")
public class AttributeServerProperties {

    /**
     * http address of attribute server
     */
    @URL
    @NotEmpty
    private String url = "http://localhost:9000";

    /**
     * Path to cities endpoint
     */
    @Pattern(regexp = "/.*", message = "Path should start with '/'")
    private String locations = "/locations";

    /**
     * Path to attributes endpoint
     */
    @Pattern(regexp = "/.*", message = "Path should start with '/'")
    private String attributes = "/attributes";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
