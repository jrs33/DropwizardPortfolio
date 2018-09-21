package com.site.joshsurette.utility;

import com.google.common.base.Throwables;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.List;

public class ElasticSearchAdminUtility {

    private final RestHighLevelClient client;

    public ElasticSearchAdminUtility(
            RestHighLevelClient client
    ) {
        this.client = client;
    }

    public List<Node> getNodes() {
        List<Node> nodes = client.getLowLevelClient().getNodes();

        try {
            client.getLowLevelClient().close();
        } catch (IOException e) {
            Throwables.propagate(e);
        }

        return nodes;
    }
}
