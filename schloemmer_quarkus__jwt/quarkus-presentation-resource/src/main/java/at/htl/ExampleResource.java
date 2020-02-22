package at.htl;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;

@Path("/hello")
@RequestScoped
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"defaultUsers"})
//    @DenyAll
//    @PermitAll
    public String hello(/*@Context HttpHeaders headers*/) {
        return "hello";
    }
}