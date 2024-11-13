package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.TerminoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/terminos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed({ "Usuario", "Administrador" })
public class TerminoApi extends AbstractGenericApi<Termino,String> {

    public TerminoApi() {
    }

    @Inject
    public TerminoApi(TerminoService service) {
        super(service);
    }

    @POST
    public Response save(@Valid Termino entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Termino entidad) {
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

    @POST
    @Path("/{id}/sinonimos")
    public Response addRecurso(@PathParam("id") String id, String sinonimo) {
        var termino = ((TerminoService)service).addSinonimo(id,sinonimo);
        return Response.ok(termino,MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}/recursos/{sinonimo}")
    public Response removeSinonimo(@PathParam("id") String id, @PathParam("sinonimo") String sinonimo) {
        var termino = ((TerminoService)service).removeSinonimo(id,sinonimo);
        return Response.ok(termino,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/sinonimos")
    public Response addRecurso(@PathParam("id") String id, List<String> sinonimos) {
        var termino = service.findOrThrow(id);
        termino.setSinonimos(sinonimos);
        service.update(termino);
        return Response.ok(termino,MediaType.APPLICATION_JSON).build();
    }

}
