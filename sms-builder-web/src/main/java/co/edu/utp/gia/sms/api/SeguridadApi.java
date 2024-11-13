package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.LoginDTO;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/seguridad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguridadApi {
    private UsuarioService service;

    public SeguridadApi() {
    }

    @Inject
    public SeguridadApi(UsuarioService service) {
        this.service = service;
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(LoginDTO login) throws InvalidAlgorithmParameterException {

        return service.login(login.username(), login.password()).map(usuario -> {
            String token = Jwt
                    .issuer("https://grid.uniquindio.edu.co")
                    .subject(login.username())
                    .groups(usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet()))
                    .expiresAt(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant())
                    // .sign(KeyUtils.generateSecretKey(SignatureAlgorithm.HS256));
                    .sign();
            return Response.ok(usuario, MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token).build();
        }).orElse(
                Response.status(Response.Status.FORBIDDEN).build());
    }
}
