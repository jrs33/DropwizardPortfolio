package com.site.joshsurette;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class SuretteConfiguration extends Configuration {

    @NotEmpty
    private String esHost;

    @NotEmpty
    private String clusterName;

    @JsonProperty("elasticSearchHost")
    public String getEsHost() {
        return esHost;
    }

    @JsonProperty("elasticSearchHost")
    public void setEsHost(String esHost) {
        this.esHost = esHost;
    }

    @JsonProperty("elasticSearchCluster")
    public String getClusterName() {
        return clusterName;
    }

    @JsonProperty("elasticSearchCluster")
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

}
