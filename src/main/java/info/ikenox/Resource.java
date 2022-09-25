package info.ikenox;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
public class Resource {

  @GET
  @Path("/hello")
  public Response hello() {
    return Response.ok("hello").build();
  }
}
