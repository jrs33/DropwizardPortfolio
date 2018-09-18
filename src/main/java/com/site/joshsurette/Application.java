package com.site.joshsurette;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application {

    public static void main(final String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        return;
    }

    @Override
    public void run(final Configuration configuration,
                    final Environment environment) {
        return;
    }

}
