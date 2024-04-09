package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.UsuarioDTO;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioApi extends AbstractGenericApi<Usuario,String>{

    private UsuarioService service;

    public UsuarioApi() {
    }

    @Inject
    public UsuarioApi(UsuarioService service) {
        this.service = service;
    }

    @POST
    public Response save(UsuarioDTO entidad) {
        var usuario = service.create(entidad.usuario(), entidad.clave());
        return createResponse(usuario);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, UsuarioDTO entidad) {
        findOrThrow(id);
        if( id != entidad.usuario().getId() ){
            throw new WebApplicationException("El usuario a modificar no coincide con los datos dados", Response.Status.BAD_REQUEST);
        }
        var usuario = service.update(entidad.usuario(), entidad.clave());
        return Response.ok(usuario,MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        return super.delete(id);
    }

    @GET
    @Path("/{id}")
    @Override
    public Response find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    public Response get() {
        return super.get();
    }

}
