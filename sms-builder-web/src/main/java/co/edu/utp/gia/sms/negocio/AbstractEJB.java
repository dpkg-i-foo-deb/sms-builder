package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 */
public abstract class AbstractEJB<E extends Entidad<TipoId>, TipoId> implements Serializable {


    private final Provider<Collection<E>> dataProvider;

    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
    @Inject
    @Getter
    protected ExceptionMessage exceptionMessage;

    public AbstractEJB() {
        dataProvider = Collections::emptyList;
    }

    public AbstractEJB(Provider<Collection<E>> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public E registrar(E entidad) {
        try {
            if (obtener(entidad.getId()) != null) {
                throw new LogicException(exceptionMessage.getRegistroExistente());
            }
            dataProvider.get().add(entidad);
            return entidad;
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void actualizar(E entidad) {
        try {
            obtenerOrThrow(entidad.getId()).updateFrom(entidad);
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void eliminar(E entidad) {
        eliminar(entidad.getId());
    }

    public void eliminar(TipoId id) {
        try {
            dataProvider.get().remove(obtenerOrThrow(id));
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public E obtener(TipoId id) {
        try {
            return dataProvider.get().stream().filter((e) -> e.getId().equals(id)).findFirst().orElse(null);
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public E obtenerOrThrow(TipoId id) {
        E registro = obtener(id);
        if (registro == null) {
            throw new LogicException(exceptionMessage.getRegistroNoEncontrado());
        }
        return registro;
    }

    public Collection<E> listar() {
        return dataProvider.get();
    }
}
