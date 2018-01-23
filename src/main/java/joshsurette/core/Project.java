package joshsurette.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/*
 * POJO to define projects. Can live as a Java object and JSON with Jackson
 */
public class Project {

    @NotEmpty
    @JsonProperty
    private String name;

    @NotEmpty
    @JsonProperty
    private String image;

    @NotEmpty
    @JsonProperty
    private String description;

    @NotEmpty
    @JsonProperty
    private List<String> languageList;

    public Project(){ }

    public Project(String name, String image, String description, List<String> languageList) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.languageList = languageList;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

}
