package CHESSim.health;

import com.codahale.metrics.health.HealthCheck;

import CHESSim.resources.LobbyResource;

public class LobbyHealthCheck extends HealthCheck {

	private LobbyResource resource;
	
	
	public LobbyHealthCheck(LobbyResource resource) {
		this.resource=resource;
	}
	
	
	@Override
	protected Result check() throws Exception {
		Result r = Result.unhealthy("faulty lobbies");
		try {
			if(resource.getLobbys() != "") {
				r=Result.healthy();
			}
		}
		catch(Exception e){
			r = Result.unhealthy("failing to retrieve lobbies");
		}
		return r;
	}

}
