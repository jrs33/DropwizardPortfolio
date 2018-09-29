import com.site.joshsurette.core.ElasticSearchClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.Matchers.eq;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RestHighLevelClient.class)
public class ElasticSearchClientTest {

    private static final SearchResponse searchResponse = new SearchResponse();
    private static final SearchRequest searchRequest = new SearchRequest();

    private static final SearchHits EMPTY_HITS = SearchHits.empty();
    private static final String[] EMPTY_INDICES = new String[]{};

    ElasticSearchClient elasticSearchClient;


    @Test
    public void testWithEmptyIndices() throws IOException {
        RestHighLevelClient mockedClient = PowerMockito.mock(RestHighLevelClient.class);
        ElasticSearchClient elasticSearchClient = new ElasticSearchClient(mockedClient);

        PowerMockito.when(mockedClient.search(eq(searchRequest), eq(RequestOptions.DEFAULT))).thenReturn(searchResponse);

        SearchHits hitsResponse = elasticSearchClient.search(EMPTY_INDICES);
        Assert.assertEquals(EMPTY_HITS, hitsResponse);
    }
}
