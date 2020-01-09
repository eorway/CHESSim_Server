package CHESSim.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/Lobbys")
public class LobbyResource{
	
	@GET
	@Path("all")
	public String getLobbys()
	{
		return "{json:\"i'm a json\"}";
	}
	
}
