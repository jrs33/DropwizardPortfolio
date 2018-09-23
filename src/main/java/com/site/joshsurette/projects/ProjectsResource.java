package com.site.joshsurette.projects;

import com.site.joshsurette.core.ElasticSearchClient;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Optional;

@Path(ProjectsResource.PROJECTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectsResource {

    static final String PROJECTS_PATH = "/projects/search";

    private static final String[] projectsIndex = new String[] {"projects"};

    private final ElasticSearchClient searchClient;

    public ProjectsResource(
            ElasticSearchClient searchClient
    ) {
        this.searchClient = searchClient;
    }

    @Path("/name")
    @GET
    public SearchResponse searchProjectsByName(
            @QueryParam("from") @DefaultValue("0") int from,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue(Project.STARTED_DATE_FIELD) String sortField,
            @QueryParam("sortDirection") Optional<String> sortOrder,
            @QueryParam("q") String name
    ) {
        QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery(Project.TITLE_FIELD, name);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.sort(sortField, sortOrder.isPresent() ? SortOrder.valueOf(sortOrder.get()) : SortOrder.DESC);

        try {
            return searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_names", e);
        }
    }

    @Path("/text")
    @GET
    public SearchResponse searchProjectsByDescription(
            @QueryParam("from") @DefaultValue("0") int from,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue(Project.STARTED_DATE_FIELD) String sortField,
            @QueryParam("sortDirection") Optional<String> sortOrder,
            @QueryParam("q") String keywords
    ) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(Project.DESCRIPTION_FIELD, keywords);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.sort(sortField, sortOrder.isPresent() ? SortOrder.valueOf(sortOrder.get()) : SortOrder.DESC);

        try {
            return searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_descriptions", e);
        }
    }
}
