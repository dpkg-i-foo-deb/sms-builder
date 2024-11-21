package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.MetadatoDTOParser;
import co.edu.utp.gia.sms.dtos.MetadatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.negocio.MetadatoServices;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/metadatos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class MetadatoApi extends AbstractGenericApi<Metadato,String> {

    private MetadatoDTOParser metadatoDTOParser;

    public MetadatoApi() {
    }

    @Inject
    public MetadatoApi(MetadatoServices service, MetadatoDTOParser metadatoDTOParser) {
        super(service);
        this.metadatoDTOParser = metadatoDTOParser;
    }

    @POST
    public Response save(@Valid MetadatoDTO entidad) {
        return super.save(metadatoDTOParser.parse(entidad));
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, @Valid MetadatoDTO entidad) {
        return super.update(id, metadatoDTOParser.parse(entidad));
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
    public Response get(@QueryParam("referencia") String referencia) {
        if( referencia == null || referencia.isEmpty()) {
            return super.get();
        } else {
            return Response.ok(((MetadatoServices)service).get(referencia),MediaType.APPLICATION_JSON).build();
        }
    }
}
