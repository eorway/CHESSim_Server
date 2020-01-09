package CHESSim;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import 

public class CHESSim_ServerApplication extends Application<CHESSim_ServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CHESSim_ServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "CHESSim_Server";
    }

    @Override
    public void initialize(final Bootstrap<CHESSim_ServerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CHESSim_ServerConfiguration configuration,
                    final Environment environment) {
        environment.jersey.register(new LobbyResource);
        // TODO: implement application
    }

}
