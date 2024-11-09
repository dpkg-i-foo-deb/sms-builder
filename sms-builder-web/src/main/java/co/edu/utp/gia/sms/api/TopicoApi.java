package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/topicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class TopicoApi extends AbstractGenericApi<Topico,String> {

    public TopicoApi() {
    }

    @Inject
    public TopicoApi(TopicoService service) {
        super(service);
    }

    @POST
    public Response save(Topico entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Topico entidad) {
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
    public Response get() {
        return super.get();
    }

}