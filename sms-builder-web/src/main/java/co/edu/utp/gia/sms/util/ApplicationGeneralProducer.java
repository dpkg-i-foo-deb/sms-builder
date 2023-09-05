package co.edu.utp.gia.sms.util;

import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.ExceptionMessageFactory;
import lombok.Getter;
import lombok.extern.java.Log;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.Locale;
/**
 * Clase utilitaria encargada de producir recursos para inyección usados por otras clases.
 */
@ApplicationScoped
@Log
public class ApplicationGeneralProducer {
    @Getter
    private static final ApplicationGeneralProducer instance = new ApplicationGeneralProducer();

    @Produces
    @Named("defaultLocale")
    public Locale getDefaultLocale(){
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            return facesContext != null ? facesContext.getViewRoot().getLocale() : Locale.ROOT;

        }catch (Throwable e){
            return Locale.ROOT;
        }
    }

    @Produces
    @Named("exceptionMessage")
    public ExceptionMessage getExceptionMessage(){
        Locale locale = getDefaultLocale();
        if( locale == null ){
            locale = Locale.ROOT;
        }
        return ExceptionMessageFactory.getInstance().getMessageInstance( locale );
    }
}
