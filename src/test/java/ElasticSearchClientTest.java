import com.site.joshsurette.core.ElasticSearchClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.eq;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RestHighLevelClient.class)
public class ElasticSearchClientTest {

    private static final SearchHits EMPTY_HITS = SearchHits.empty();
    private static final String[] EMPTY_INDICES = new String[]{};
    private static final String[] VALID_INDICES = new String[]{"index_1"};

    private static final SearchResponse searchResponse = new SearchResponse();
    private static final SearchRequest validSearchRequest = new SearchRequest(VALID_INDICES);

    RestHighLevelClient restHighLevelClient;
    ElasticSearchClient elasticSearchClient;

    @Before
    public void setUp() throws IOException {
        restHighLevelClient = PowerMockito.mock(RestHighLevelClient.class);
        elasticSearchClient = new ElasticSearchClient(restHighLevelClient);

        PowerMockito.when(restHighLevelClient.search(any(), any(RequestOptions.class))).thenReturn(searchResponse);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyIndices() throws Exception {
        elasticSearchClient.search(EMPTY_INDICES);
    }

    @Test
    public void testWithValidIndices() throws Exception {
        SearchHits hits = elasticSearchClient.search(VALID_INDICES);

        Assert.assertEquals(EMPTY_HITS, hits);
    }
}
