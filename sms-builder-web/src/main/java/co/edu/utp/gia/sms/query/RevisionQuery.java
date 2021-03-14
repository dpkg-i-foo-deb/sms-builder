package co.edu.utp.gia.sms.query;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = RevisionQuery.REVISION_GET_ALL, query = "select p from Revision p")
@NamedQuery(name = RevisionQuery.REVISION_GET_ALL_RELATED, query = "select distinct p from Revision p left join p.revisores as r where p.propietario.id = :id or r.id = :id")
@NamedQuery(name = RevisionQuery.REVISION_TOTAL_REFERENCIAS, query = "select count(1) from Referencia r  where r.revision.id = :id")
@NamedQuery(name = RevisionQuery.REVISION_TOTAL_REFERENCIAS_REPETIDAS, query = "select count(1) from Referencia r  where r.revision.id = :id and r.filtro = 0")
@NamedQuery(name = RevisionQuery.REVISION_TOTAL_REFERENCIAS_SELECCIONADAS, query = "select count(1) from Referencia r  where r.revision.id = :id and r.filtro >= 3")
public class RevisionQuery extends Queries{
    /**
     * Consulta que permite obtener las revisiones registradas en el sistema <br />
     * <code>select p from Revision p  </code>
     */
    public static final String REVISION_GET_ALL = "Revision.getAll";
    /**
     * Consulta que permite obtener las revisiones registradas en el sistema <br />
     *
     * @param :id
     * <code>select p from Revision p inner join p.revisores as r where p.propietario.id = :id or r.id = :id</code>
     */
    public static final String REVISION_GET_ALL_RELATED = "Revision.getAllRelated";
    /**
     * Consulta que permite obtener el total de referencias de una Revision
     *
     * @param id Id de la Revision
     *           <code>select count(1) from Referencia r  where r.revision.id = :id</code>
     */
    public static final String REVISION_TOTAL_REFERENCIAS ="Revision.totalReferencias";
    /**
     * Consulta que permite obtener el total de referencias repetidas de una Revision
     *
     * @param id Id de la Revision
     *           <code>select count(1) from Referencia r  where r.revision.id = :id and filtro = 0</code>
     */
    public static final String REVISION_TOTAL_REFERENCIAS_REPETIDAS ="Revision.totalReferenciasRepetidas";
    /**
     * Consulta que permite obtener el total de referencias repetidas de una Revision
     *
     * @param id Id de la Revision
     *           <code>select count(1) from Referencia r  where r.revision.id = :id and filtro >= 3</code>
     */
    public static final String REVISION_TOTAL_REFERENCIAS_SELECCIONADAS = "Revision.totalReferenciasSeleccionadas";
}