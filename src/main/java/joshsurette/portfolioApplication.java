package joshsurette;

import joshsurette.core.Project;
import joshsurette.resources.ApplicationResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class portfolioApplication extends Application<portfolioConfiguration> {

    public static void main(final String[] args) throws Exception {
        new portfolioApplication().run(args);
    }

    @Override
    public String getName() {
        return "portfolio";
    }

    @Override
    public void initialize(final Bootstrap<portfolioConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final portfolioConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(
                new ApplicationResource(configuration.getProjects())
        );

    }

}
