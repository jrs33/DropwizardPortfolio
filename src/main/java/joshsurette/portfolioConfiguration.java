package joshsurette;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import joshsurette.core.Project;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.util.List;

public class portfolioConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty()
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

}
