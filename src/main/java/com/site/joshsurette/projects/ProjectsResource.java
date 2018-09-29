package com.site.joshsurette.projects;

import com.codahale.metrics.annotation.Timed;
import com.site.joshsurette.core.ElasticSearchClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsResource.class);

    private static final String PROJECTS = "projects";
    private static final String[] projectsIndex = new String[] {PROJECTS};

    private final ElasticSearchClient searchClient;

    public ProjectsResource(
            ElasticSearchClient searchClient
    ) {
        this.searchClient = searchClient;
    }

    @Path("/name")
    @Timed
    @GET
    public SearchHits searchProjectsByName(
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

        SearchHits response;
        try {
            response = searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_names", e);
        }

        LOGGER.info(String.format("searching by name query %s yielded %d hits", queryBuilder.toString(), response.totalHits));
        return response;
    }

    @Path("/text")
    @Timed
    @GET
    public SearchHits searchProjectsByDescription(
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

        SearchHits response;
        try {
            response = searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_descriptions", e);
        }

        LOGGER.info(String.format("searching by text query %s yielded %d hits", queryBuilder.toString(), response.totalHits));
        return response;
    }
}
