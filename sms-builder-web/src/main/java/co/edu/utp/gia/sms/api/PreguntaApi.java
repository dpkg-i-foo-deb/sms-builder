package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.PreguntaDTOParser;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/preguntas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class PreguntaApi extends AbstractGenericApi<Pregunta,String> {

    private PreguntaService service;
    private TopicoService topicoService;
    private PreguntaDTOParser preguntaDTOParser;

    public PreguntaApi() {
    }

    @Inject
    public PreguntaApi(PreguntaService service, TopicoService topicoService, PreguntaDTOParser preguntaDTOParser) {
        super(service);
        this.topicoService = topicoService;
        this.preguntaDTOParser = preguntaDTOParser;
        this.service = service;
    }

    @POST
    public Response save(PreguntaDTO entidad) {
        var pregunta = preguntaDTOParser.parse(entidad);
        return super.save(pregunta);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, PreguntaDTO entidad) {
        var pregunta = preguntaDTOParser.parse(entidad);
        return super.update(id, pregunta);
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

    @POST
    @Path("/{id}/topicos")
    public Response add(@PathParam("id") String id, Topico entidad) {
        var pregunta = service.findOrThrow(id);
        ((PreguntaService)service).add(pregunta,entidad);
        return Response.ok(pregunta,MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}/topicos/{idTopico}")
    public Response remove(@PathParam("id") String id,@PathParam("idTopico") String idTopico) {
        var pregunta = service.findOrThrow(id);
        var topico = topicoService.findOrThrow(idTopico);
        ((PreguntaService)service).remove(pregunta,topico);
        return Response.ok(pregunta,MediaType.APPLICATION_JSON).build();
    }

}
