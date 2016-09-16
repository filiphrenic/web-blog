package hr.fer.zemris.app.dao;

import hr.fer.zemris.app.dao.jpa.JPADAOImpl;

/**
 * Singleton razred koji ima privatnu instancu DAO-a i vraća ju metodom getDao()
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public final class DAOProvider {

    private static final DAO USED_DAO = new JPADAOImpl();

    /**
     * Vraća {@link DAO} koji se trenutno koristi.
     * 
     * @return dao
     */
    public static DAO getDAO() {
        return USED_DAO;
    }

    /**
     * Can't be created.
     */
    private DAOProvider() {
    }

}
