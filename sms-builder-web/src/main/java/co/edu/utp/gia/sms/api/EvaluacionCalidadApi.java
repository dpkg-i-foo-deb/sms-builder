package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.EvaluacionCalidadDTOParser;
import co.edu.utp.gia.sms.dtos.EvaluacionCalidadDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.negocio.EvaluacionCalidadService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/evaluacionescalidad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class EvaluacionCalidadApi extends AbstractGenericApi<EvaluacionCalidad,String> {

    private EvaluacionCalidadDTOParser evaluacionCalidadDTOParser;

    public EvaluacionCalidadApi() {
    }

    @Inject
    public EvaluacionCalidadApi(EvaluacionCalidadService service, EvaluacionCalidadDTOParser evaluacionCalidadDTOParser) {
        super(service);
        this.evaluacionCalidadDTOParser = evaluacionCalidadDTOParser;
    }

    @POST
    public Response save(@Valid EvaluacionCalidadDTO entidad) {
        return super.save(evaluacionCalidadDTOParser.parse(entidad));
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, @Valid EvaluacionCalidadDTO entidad) {
        return super.update(id, evaluacionCalidadDTOParser.parse(entidad));
    }

    @DELETE
    @Path("/{id}")
    @Override
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
    @Override
    public Response get() {
        return super.get();
    }

    @PUT
    public Response evaluacionAutomatica() {
        ((EvaluacionCalidadService)service).evaluacionAcutomatica();
        return Response.ok().build();
    }
}
