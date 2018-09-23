package com.site.joshsurette.utility;

import com.fasterxml.jackson.databind.JsonNode;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RequestOptions;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @PUT
    @Path("/{indexName}")
    public void createIndex(
            @PathParam("indexName") String indexName,
            CreateIndexRequest createIndexRequest
    ) {
        elasticSearchAdminUtility.createIndex(createIndexRequest, RequestOptions.DEFAULT);
    }
}
