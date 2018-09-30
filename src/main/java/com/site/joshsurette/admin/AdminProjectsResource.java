package com.site.joshsurette.admin;

import com.site.joshsurette.core.ElasticSearchClient;
import com.site.joshsurette.projects.ImmutableProject;
import org.elasticsearch.action.index.IndexResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path(AdminProjectsResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminProjectsResource {

    public static final String PATH = "/admin/projects";

    private final ElasticSearchClient elasticSearchClient;

    public AdminProjectsResource(
            ElasticSearchClient elasticSearchClient
    ) {
        this.elasticSearchClient = elasticSearchClient;
    }

    @Path("/index/{indexName}")
    @POST
    public IndexResponse createProject(
        @PathParam("indexName") String indexName,
        ImmutableProject project
    ) {
        if(indexName.isEmpty()) {
            throw new WebApplicationException("invalid index passed", Response.Status.BAD_REQUEST);
        }

        try {
            return elasticSearchClient.index(indexName, project);
        } catch (IOException e) {
            throw new WebApplicationException("error occurred while indexing", e);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("unable to index document into " + indexName, Response.Status.BAD_REQUEST);
        }
    }
}
