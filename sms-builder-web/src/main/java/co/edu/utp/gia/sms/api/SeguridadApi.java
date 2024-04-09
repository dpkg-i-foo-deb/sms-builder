package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.LoginDTO;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/seguridad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguridadApi {
    private UsuarioService service;

    public SeguridadApi() {
    }

    @Inject
    public SeguridadApi(UsuarioService service) {
        this.service = service;
    }

    @POST
    @Path("/login")
    public Response login(LoginDTO login) {
        var usuario = service.login(login.username(), login.password());
        if( usuario != null ){
            return Response.ok(usuario,MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
