package co.edu.utp.gia.sms.importutil.bibtex;

/**
 * Clase utilitaria encargada de procesar una cade perteneciente a una referencia bibtex.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public class BibtexStringUtil {
    private static final String[] CARACTERES_LATEX  = {"\\c{c}","\\c{C}","\\{","\\}","\\","'{a}","'{e}","'{i}","'{o}","'{u}","~{a}","~{o}",
            "'{A}","'{E}","'{I}","'{O}","'{U}","~{A}","~{O}",
    "`{a}","`{e}","`{i}","`{o}","`{u}","`{A}","`{E}","`{I}","`{O}","`{U}","^{a}","^{e}","^{i}","^{o}","^{u}"
            ,"^{A}","^{E}","^{I}","^{O}","^{U}","\"{a}","\"{e}","\"{i}","\"{o}","\"{u}","\"{A}","\"{E}","\"{I}","\"{O}","\"{U}",
            "{","}","\t","\r"};
    private static final String[] CARACTERES_NORMAL = {"ç","Ç","\t","\r","","á","é","í","ó","ú","ã","õ","Á","É","Í","Ó","Ú","Ã","Õ",
    "à","è","ì","ò","ù","À","È","Ì","Ò","Ù","â","ê","î","ô","û","Â","Ê","Î","Ô","Û","ä","ë","ï","ö","ü","Ä","Ë","Ï","Ö","Ü",
            "","","{","}"};

    public static String parse(String texto){
        for (int i = 0; i < CARACTERES_LATEX.length; i++) {
            texto = texto.replace(CARACTERES_LATEX[i],CARACTERES_NORMAL[i] );
            //texto = texto.replaceAll(CARACTERES_LATEX[i],CARACTERES_NORMAL[i] );
        }
        return texto;
    }
}
