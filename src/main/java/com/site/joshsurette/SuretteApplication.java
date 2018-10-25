package com.site.joshsurette;

import com.site.joshsurette.admin.AdminProjectsResource;
import com.site.joshsurette.core.ElasticSearchClient;
import com.site.joshsurette.core.servlets.CorsFilter;
import com.site.joshsurette.projects.ProjectsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.HttpHost;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class SuretteApplication extends Application<SuretteConfiguration> {

    private static final String PROTOCOL = "http";

    public static void main(final String[] args) throws Exception {
        new SuretteApplication().run(args);
    }

    @Override
    public String getName() {
        return "surette-personal-site";
    }

    @Override
    public void initialize(final Bootstrap<SuretteConfiguration> bootstrap) { }

    @Override
    public void run(final SuretteConfiguration configuration,
                    final Environment environment) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(configuration.getEsHost(), Integer.valueOf(configuration.getEsPort()), PROTOCOL)
                )
        );
        ElasticSearchClient elasticSearchClient = new ElasticSearchClient(client);

        // Creating Resources
        ProjectsResource projectsResource = new ProjectsResource(elasticSearchClient);
        AdminProjectsResource adminProjectsResource = new AdminProjectsResource(elasticSearchClient);

        // Registering Resources
        environment.jersey().register(projectsResource);
        environment.jersey().register(adminProjectsResource);

//        environment.servlets().addFilter("CORS", new CorsFilter())
//                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
