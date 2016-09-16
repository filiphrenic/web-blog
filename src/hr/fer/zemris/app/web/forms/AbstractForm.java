package hr.fer.zemris.app.web.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class contains methods that every form has.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public abstract class AbstractForm {

    // name of property, error
    final Map<String, String> errors = new HashMap<>();

    /**
     * @return <code>true</code> if form contains errors
     */
    public boolean imaGreske() {
        return !errors.isEmpty();
    }

    /**
     * @param key tested field, will return true if there's an error for this property
     * @return <code>true</code> if there's an error
     */
    public boolean imaGresku(String key) {
        return errors.containsKey(key);
    }

    /**
     * @param key defines which error you want
     * @return error message
     */
    public String dohvatiGresku(String key) {
        return errors.get(key);
    }

    /**
     * Dodaje gresku u mapu grešaka.
     * 
     * @param key property koji ima grešku
     * @param greska poruka greske
     */
    public void dodajGresku(String key, String greska) {
        errors.put(key, greska);
    }

    /**
     * Ako je parametar <code>null</code> vraća prazan string, inače vraća taj isti string bez leading i trailing
     * spaces.
     * 
     * @param o string za obraditi
     * @return obrađen string
     */
    String obradi(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    /**
     * Fills the form using a request.
     * 
     * @param req request that provides data
     */
    public abstract void fillFromRequest(HttpServletRequest req);

    /**
     * Provjerava ima li grešaka u propertyima.
     */
    public abstract void validate();

}
