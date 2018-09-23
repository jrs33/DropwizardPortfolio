package com.site.joshsurette.core;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ElasticSearchClient {

    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchClient(
            RestHighLevelClient restHighLevelClient
    ) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public SearchResponse search(String[] indices) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indices);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        restHighLevelClient.close();

        return searchResponse;
    }

    public SearchResponse search(String[] indices, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indices, searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        restHighLevelClient.close();

        return searchResponse;
    }
}
