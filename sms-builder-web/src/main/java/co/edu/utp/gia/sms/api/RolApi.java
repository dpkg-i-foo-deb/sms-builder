package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.RolDTOParser;
import co.edu.utp.gia.sms.dtos.RolDTO;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RolService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@ApplicationScoped
@Path("/roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class RolApi extends AbstractGenericApi<Rol,String> {

    private RolDTOParser rolDTOParser;

    public RolApi() {
    }

    @Inject
    public RolApi(RolService service, RolDTOParser rolDTOParser) {
        super(service);
        this.rolDTOParser = rolDTOParser;
    }

    @POST
    public Response save(RolDTO entidad) {
        return super.save(rolDTOParser.parse(entidad));
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, RolDTO entidad) {
        return super.update(id, rolDTOParser.parse(entidad));
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
    public Response get(@QueryParam("name") String name) {
        Collection<Rol> roles;
        if( name != null ){
            roles = ((RolService)service).findByName(name);
        } else {
            roles = service.get();
        }
        return Response.ok(roles,MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/{id}/recursos")
    public Response addRecurso(@PathParam("id") String id, String url) {
        ((RolService)service).addRecurso(id,url);
        return find(id);
    }

    @DELETE
    @Path("/{id}/recursos/{idRecurso}")
    public Response removeRecurso(@PathParam("id") String id, @PathParam("idRecurso") String idRecurso) {
        var rol = ((RolService) service).removeRecurso(id,idRecurso);
        return Response.ok(rol,MediaType.APPLICATION_JSON).build();
    }

}
