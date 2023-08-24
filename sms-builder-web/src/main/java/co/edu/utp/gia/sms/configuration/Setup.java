package co.edu.utp.gia.sms.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Startup
@Singleton
public class Setup implements SetupInterface {

    @Inject
    private RecursosSetup recursosSetup;
    @Inject
    private RolSetup rolSetup;
    @Inject
    private UsuariosSetup usuariosSetup;
    @Inject
    private PasosSetup pasosSetup;
    @Inject
    private RevisionSetup revisionSetup;
    @Inject
    private ProcesoSetup procesoSetup;
    @Inject
    private AtributosCalidadSetup atributosCalidadSetup;
    @Inject
    private FuentesSetup fuentesSetup;
    @PostConstruct
    public void setup() {
        try{
            revisionSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            atributosCalidadSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            fuentesSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try {
            recursosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            rolSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            usuariosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            pasosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }

        try{
            procesoSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }

    }

}
