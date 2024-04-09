package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.RevisionDTO;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/revision")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RevisionApi  {

    private RevisionService service;
    private ProcesoService procesoService;
    public RevisionApi() {
    }

    @Inject
    public RevisionApi(RevisionService service,ProcesoService procesoService) {
        this.service = service;
        this.procesoService = procesoService;
    }

    @PUT
    public Response save(@Valid RevisionDTO entidad) {
        var revision = service.save(entidad.nombre(), entidad.descripcion());
        return Response.ok(revision,MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("")
    public Response find() {
        var revision = service.get();
        return Response.ok(revision,MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/pasoActual")
    public Response getPasoActual() {
        var paso = service.getPasoActual();
        return Response.ok(paso,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/pasoActual")
    public Response updatePasoActual(@NotBlank String id) {
        var paso = procesoService.findOrThrow(id);
        service.changePasoActual(paso);
        return Response.ok().build();
    }



//    @PUT
//    @Path("/{id}")
//    public Response update(@PathParam("id") String id, Referencia entidad) {
//        return super.update(id, entidad);
//    }


}
