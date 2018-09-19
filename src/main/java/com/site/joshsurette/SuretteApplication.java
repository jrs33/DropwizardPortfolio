package com.site.joshsurette;

import com.site.joshsurette.utility.ElasticSearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.log4j.Logger;

public class SuretteApplication extends Application<SuretteConfiguration> {
    private static final Logger logger = Logger.getLogger(SuretteApplication.class);

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
        final ElasticSearchResource elasticSearchResource = new ElasticSearchResource();

        environment.jersey().register(elasticSearchResource);
    }

}
