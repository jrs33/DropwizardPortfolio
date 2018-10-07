package com.site.joshsurette.projects;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
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
import javax.ws.rs.core.Response;
import java.io.IOException;

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
            @QueryParam("q") Optional<String> name
    ) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        if(name.isPresent()) {
            QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery(Project.TITLE_FIELD, name.get());
            LOGGER.info(String.format("searching by name with query %s", queryBuilder.toString()));

            searchSourceBuilder.query(queryBuilder);
        }

        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.sort(sortField, sortOrder.isPresent() ? SortOrder.valueOf(sortOrder.get()) : SortOrder.DESC);

        try {
            return searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_names", e);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("unable to process input", Response.Status.BAD_REQUEST);
        }
    }

    @Path("/text")
    @Timed
    @GET
    public SearchHits searchProjectsByDescription(
            @QueryParam("from") @DefaultValue("0") int from,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue(Project.STARTED_DATE_FIELD) String sortField,
            @QueryParam("sortDirection") Optional<String> sortOrder,
            @QueryParam("q") Optional<String> keywords
    ) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        if(keywords.isPresent()) {
            QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery(Project.DESCRIPTION_FIELD, keywords.get());
            LOGGER.info(String.format("searching text with query %s", queryBuilder.toString()));

            searchSourceBuilder.query(queryBuilder);
        }

        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.sort(sortField, sortOrder.isPresent() ? SortOrder.valueOf(sortOrder.get()) : SortOrder.DESC);

        try {
            return searchClient.search(projectsIndex, searchSourceBuilder);
        } catch (IOException e) {
            throw new WebApplicationException("error_searching_project_descriptions", e);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("unable to process input", Response.Status.BAD_REQUEST);
        }
    }
}
