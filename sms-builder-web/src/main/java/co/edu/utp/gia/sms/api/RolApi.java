package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RolService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@ApplicationScoped
@Path("/referencias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolApi extends AbstractGenericApi<Rol,String> {

    public RolApi() {
    }

    @Inject
    public RolApi(RolService service) {
        super(service);
    }

    @POST
    public Response save(Rol entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Rol entidad) {
        return super.update(id, entidad);
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
    public Response addRecurso(@PathParam("id") String id, Recurso recurso) {
        ((RolService)service).addRecurso(id,recurso);
        return find(id);
    }

    @DELETE
    @Path("/{id}/recursos/{idRecurso}")
    public Response removeTopico(@PathParam("id") String id, @PathParam("idRecurso") String idRecurso) {
        var rol = ((RolService) service).removeRecurso(id,idRecurso);
        return Response.ok(rol,MediaType.APPLICATION_JSON).build();
    }

}
