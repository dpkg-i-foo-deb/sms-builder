package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.exceptions.LogicException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Rol}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class RolService extends AbstractGenericService<Rol, String> {

    @Inject
    RecursoService recursoService;

    public RolService() {
        super(DB.root.getProvider(Rol.class));
    }

    /**
     * Permite adicionar un recurso a un rol
     * @param id Id del recurso al que se desea adicionar un recurso
     * @param recurso Recurso a ser adicionado
     */
    public void addRecurso(String id,Recurso recurso){
        Rol rol = findOrThrow(id);
        rol.getRecursos().add(recurso);
    }

    /**
     * Permite adicionar un recurso a un rol
     * @param id Id del recurso al que se desea adicionar un recurso
     * @param urlRecurso Recurso a ser adicionado
     */
    public void addRecurso(String id,String urlRecurso){
        var recurso = recursoService.findByUrl(urlRecurso);
        if(recurso == null) {
            throw new LogicException(exceptionMessage.getRegistroNoEncontrado(), Response.Status.NOT_FOUND);
        }
        addRecurso(id,recurso);
    }

    /**
     * Permite remover un recurso a un rol
     * @param id Id del recurso al que se desea adicionar un recurso
     * @param idRecurso Recurso a ser removido
     */
    public Rol removeRecurso(String id,String idRecurso){
        Rol rol = findOrThrow(id);
        var recurso = rol.getRecursos().stream().filter( r -> r.getId().equals(idRecurso) ).findAny();
        recurso.ifPresent(rol.getRecursos()::remove);
        return rol;
    }

    /**
     * Permite obtener los roles que correspondan al nombre dado
     * @param nombre Nombre del rol que se desea buscar
     * @return List de los roles con el nombre dado.
     */
    public List<Rol> findByName(String nombre) {
        return dataProvider.get().stream().filter( rol -> rol.getNombre().equals(nombre) ).toList();
    }

    /**
     * Permite obtener los roles que correspondan a los nombres dados
     * @param roles Listado de nombres de los roles que se desea buscar
     * @return List de los roles con los nombres dados.
     */
    public List<Rol> findByNames(List<String> roles) {
        if(roles != null && !roles.isEmpty()) {
            return roles.stream().map(this::findByName).flatMap(List::stream).toList();
        }
        return Collections.emptyList();
    }
}
