package CHESSim.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game")
public class GameResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getGameInfo(@PathParam("id") int id )
	{
		return "{\n" + 
				"	\"board "+id+"\": [\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0],\n" + 
				"		[0,0,0,0,0,0,0,0]\n" + 
				"	],\n" + 
				"	\"age\": 1033,\n" + 
				"	\"lastmove\": 0,\n" + 
				"	\"active_team\": 1\n" + 
				"}";
	}
	
	
	
}
