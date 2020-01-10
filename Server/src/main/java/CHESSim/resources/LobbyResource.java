package CHESSim.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Lobbies")
public class LobbyResource{
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLobbys()
	{
		return "[\n" + 
				"    {\n" + 
				"        \"name\": \"Meine Session\",\n" + 
				"        \"password\": null\n" + 
				"    },\n" + 
				"    {\n" + 
				"        \"name\": \"Seine Session\",\n" + 
				"        \"password\": null\n" + 
				"    }\n" + 
				"]";
	}
	
}
