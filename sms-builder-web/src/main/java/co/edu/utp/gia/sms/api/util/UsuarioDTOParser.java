package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.UsuarioDTO;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RolService;
import co.edu.utp.gia.sms.util.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioDTOParser implements ParseApiUtil<Usuario, UsuarioDTO> {

    private final RolService rolService;

    @Inject
    public UsuarioDTOParser(RolService rolService) {
        this.rolService = rolService;
    }

    @Override
    public Usuario parse(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuario.getId());
        usuario.setNombreUsuario(usuarioDTO.nombreUsuario());
        usuario.setEmail(usuarioDTO.email());
        usuario.setEstado(usuarioDTO.estado());
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setClave(PasswordUtil.hashedPassword(usuarioDTO.clave()));
        usuario.setRoles(rolService.findByNames(usuarioDTO.roles()));
        return usuario;
    }
}
