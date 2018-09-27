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

public class ElasticSearchClient {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchClient.class);
    private static final SearchHits EMPTY_HITS = SearchHits.empty();

    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchClient(
            RestHighLevelClient restHighLevelClient
    ) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public SearchHits search(final String[] indices) throws IOException {
        if(!isValidIndiceArgument(indices)) {
            logger.error("invalid indices passed returning empty hits: " + indices);
            return EMPTY_HITS;
        }

        SearchRequest searchRequest = new SearchRequest(indices);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        restHighLevelClient.close();

        return searchResponse.getHits();
    }

    public SearchHits search(final String[] indices, final SearchSourceBuilder searchSourceBuilder) throws IOException {
        if(!isValidIndiceArgument(indices)) {
            logger.error("invalid indices passed returning empty hits: " + indices);
            return EMPTY_HITS;
        }

        SearchRequest searchRequest = new SearchRequest(indices, searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        restHighLevelClient.close();

        return searchResponse.getHits();
    }

    private static boolean isValidIndiceArgument(final String[] indices) {
        return indices.length > 0;
    }
}
