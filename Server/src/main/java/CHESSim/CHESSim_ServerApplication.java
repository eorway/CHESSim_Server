package CHESSim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

import com.fasterxml.jackson.databind.ObjectMapper;

import CHESSim.core.Game;
import CHESSim.resources.GameResource;
import CHESSim.resources.LobbyResource;
import io.dropwizard.Application;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    	
    	ObjectMapper jsonMapper = new ObjectMapper();
    	ArrayList<Game> games = new ArrayList();
    	games.add(new Game());
    	games.add(new Game());
    	games.add(new Game());
    	games.add(new Game());
    	
    	File saveFile = new File("saveFile");
    	try {
			saveFile.createNewFile();
			jsonMapper.writeValue(saveFile, games);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


    @Override
    public void run(final CHESSim_ServerConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new LobbyResource());
        environment.jersey().register(new GameResource());
    }

}
