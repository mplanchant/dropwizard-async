package com.logiccache;

import com.logiccache.health.BookHealthCheck;
import com.logiccache.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AsyncApplication extends Application<AsyncConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AsyncApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-async";
    }

    @Override
    public void initialize(final Bootstrap<AsyncConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AsyncConfiguration configuration, final Environment environment) {
        environment.healthChecks().register("book", new BookHealthCheck());
        environment.jersey().register(new BookResource());
    }

}
