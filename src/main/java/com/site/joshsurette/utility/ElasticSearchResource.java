package com.site.joshsurette.utility;

import com.codahale.metrics.annotation.Timed;
import org.elasticsearch.index.Index;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(ElasticSearchResource.ELASTIC_RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElasticSearchResource {

    public static final String ELASTIC_RESOURCE_PATH = "/elastic";

    @Timed
    @GET
    public Index getApplicationIndeces() { return null; }
}
