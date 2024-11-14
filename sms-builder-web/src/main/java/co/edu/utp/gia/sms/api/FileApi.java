package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.ReferenceParse;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import co.edu.utp.gia.sms.negocio.FuenteService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import co.edu.utp.gia.sms.negocio.RevisionService;
import co.edu.utp.gia.sms.util.json.JsonExporter;
import co.edu.utp.gia.sms.util.json.JsonImporter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ApplicationScoped
@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "Usuario", "Administrador" })
public class FileApi {

    @Inject
    FuenteService fuenteService;
    @Inject
    ReferenciaService referenciaService;
    @Inject
    RevisionService revisionService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBackupFile(InputStream inputStream) throws Exception {
        try {
            var revisionRestored = JsonImporter.INSTANCE.importFromJson( inputStream );
            revisionService.restore(revisionRestored);
        } catch (IOException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        return Response.ok("{message:'Revisión restaurada'}").build();
    }

    @POST
    @Path("/{id}/{idFuente}/{tipoArchivo}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@PathParam("id") String id,@PathParam("idFuente") String idFuente,@PathParam("tipoArchivo") String tipoArchivo,InputStream inputStream) throws Exception {
        List<Referencia> referencias = procesarArchivo(inputStream,tipoArchivo,fuenteService.findOrThrow(idFuente));
        referenciaService.save(referencias, id);
        return Response.ok("{message:'Se importaron "+referencias.size()+" referencias'}").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportFiles() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonExporter.INSTANCE.toJsonFile(revisionService.get(),outputStream);
        //("application/zip","sms-builder.zip",(os)-> JsonExporter.INSTANCE.toJsonFile(revision,os));
        return Response.ok(outputStream.toByteArray())
                .header("Content-Disposition", "attachment; filename=\"sms-builder.zip\"")
                .build();
    }

    private List<Referencia> procesarArchivo( InputStream inputStream, String tipoArchivo,Fuente fuente){
        System.out.println("TIPO ARCHIVO "+tipoArchivo);
        ReferenceParse parser = FileMultipleRegisterParseFactory
                .getInstance(TipoArchivo.valueOf(tipoArchivo), fuente );
        return parser.parse(inputStream);
    }

}
