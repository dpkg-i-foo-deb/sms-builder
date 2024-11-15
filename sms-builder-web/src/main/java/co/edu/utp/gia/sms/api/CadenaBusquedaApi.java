package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.api.util.CadenaBusquedaDTOParser;
import co.edu.utp.gia.sms.dtos.CadenaBusquedaDTO;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.negocio.CadenaBusquedaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/cadenasbusqueda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class CadenaBusquedaApi extends AbstractGenericApi<CadenaBusqueda,String> {

    private CadenaBusquedaDTOParser cadenaBusquedaDTOParser;

    public CadenaBusquedaApi() {
    }

    @Inject
    public CadenaBusquedaApi(CadenaBusquedaService service, CadenaBusquedaDTOParser cadenaBusquedaDTOParser) {
        super(service);
        this.cadenaBusquedaDTOParser = cadenaBusquedaDTOParser;
    }

    @POST
    public Response save(@Valid CadenaBusquedaDTO entidad) {
        return super.save(cadenaBusquedaDTOParser.parse(entidad));
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, @Valid CadenaBusquedaDTO entidad) {
        return super.update(id, cadenaBusquedaDTOParser.parse(entidad));
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
        if( "__default__".equals(id)){
            var cadena = new CadenaBusqueda();
            cadena.setConsulta(((CadenaBusquedaService) service).sugerirConsulta());
            return Response.ok(cadena,MediaType.APPLICATION_JSON).build();
        }
        return super.find(id);
    }

    @GET
    @Override
    public Response get() {
        return super.get();
    }
}
