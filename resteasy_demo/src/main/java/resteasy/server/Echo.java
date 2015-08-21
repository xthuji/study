package resteasy.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path(value = "echo")
public class Echo {
	@GET
	@Path(value = "/{message}")
	public String echoService(@PathParam("message") String message) {
		return "hello, " + message;
	}
}