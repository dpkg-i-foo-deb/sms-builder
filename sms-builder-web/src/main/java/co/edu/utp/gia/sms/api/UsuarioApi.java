package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.UsuarioDTOParser;
import co.edu.utp.gia.sms.dtos.UsuarioDTO;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class UsuarioApi extends AbstractGenericApi<Usuario,String>{

    private UsuarioService service;
    private UsuarioDTOParser usuarioDTOParser;

    public UsuarioApi() {
    }

    @Inject
    public UsuarioApi(UsuarioService service, UsuarioDTOParser usuarioDTOParser) {
        super(service);
        this.service = service;
        this.usuarioDTOParser = usuarioDTOParser;
    }

    @POST
    public Response save(UsuarioDTO entidad) {
        var user = usuarioDTOParser.parse(entidad);
        var usuario = service.create(user, entidad.claveConfirmada());
        return createResponse(usuario);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, UsuarioDTO entidad) {
        findOrThrow(id);
        var usuarioData = usuarioDTOParser.parse(entidad);
        if( !id.equals(usuarioData.getId()) ){
            throw new WebApplicationException("El usuario a modificar no coincide con los datos dados", Response.Status.BAD_REQUEST);
        }
        var usuario = service.update(usuarioData, entidad.clave());
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

    @GET
    @Path("/{id}/pasoActual")
    public Response getPasoActual(@PathParam("id") String id) {
        var paso = service.findOrThrow(id).getPasoActual();
        return Response.ok(paso,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/pasoActual")
    public Response updatePasoActual(@PathParam("id") @NotBlank String id,@NotBlank String idPaso) {
        return Response.ok(service.updatePasoActual(id,idPaso)).build();
    }

}
