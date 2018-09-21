package com.site.joshsurette.utility;

import org.elasticsearch.client.Node;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(ElasticSearchResource.ELASTIC_RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElasticSearchResource {

    public static final String ELASTIC_RESOURCE_PATH = "/elastic";

    private final ElasticSearchAdminUtility elasticSearchAdminUtility;

    public ElasticSearchResource(
            ElasticSearchAdminUtility elasticSearchAdminUtility
    ) {
        this.elasticSearchAdminUtility = elasticSearchAdminUtility;
    }

    @GET
    public List<Node> getSearchNodes() {
        return elasticSearchAdminUtility.getNodes();
    }
}
