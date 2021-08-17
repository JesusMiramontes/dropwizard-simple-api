package runner;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
