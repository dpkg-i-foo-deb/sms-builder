package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.query.referencia.ReferenciaGetTags;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class TagApi {
    @GET
    @Path("")
    public Response find() {
        var tags = ReferenciaGetTags.createQuery().toList();
        return Response.ok(tags,MediaType.APPLICATION_JSON).build();
    }
}
