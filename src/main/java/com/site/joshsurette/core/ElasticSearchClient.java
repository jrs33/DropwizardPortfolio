package com.site.joshsurette.core;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class ElasticSearchClient {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchClient.class);
    private static final SearchHits EMPTY_HITS = SearchHits.empty();

    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchClient(
            RestHighLevelClient restHighLevelClient
    ) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public SearchHits search(final String[] indices) throws IOException, IllegalArgumentException {

        if(!isValidIndiceArgument(indices)) {
            throw new IllegalArgumentException("invalid indices passed returning empty hits");
        }

        SearchRequest searchRequest = new SearchRequest(indices);
        return executeSearch(searchRequest);
    }

    public SearchHits search(
            final String[] indices,
            final SearchSourceBuilder searchSourceBuilder
    ) throws IOException, IllegalArgumentException {

        if(!isValidIndiceArgument(indices)) {
            throw new IllegalArgumentException("invalid indices passed returning empty hits");
        }

        SearchRequest searchRequest = new SearchRequest(indices, searchSourceBuilder);
        return executeSearch(searchRequest);
    }

    private SearchHits executeSearch(SearchRequest searchRequest) throws IOException {
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return Objects.nonNull(searchResponse) && Objects.nonNull(searchResponse.getHits()) ?
                    searchResponse.getHits() :
                    EMPTY_HITS;
        } catch (Exception e) {
            logger.error("unexpected exception thrown when executing search", e);
        } finally {
            restHighLevelClient.close();
        }
        return EMPTY_HITS;
    }

    private static boolean isValidIndiceArgument(final String[] indices) {
        return indices.length > 0;
    }
}
