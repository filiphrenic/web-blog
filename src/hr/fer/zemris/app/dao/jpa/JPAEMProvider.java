package hr.fer.zemris.app.dao.jpa;

import hr.fer.zemris.app.dao.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Ovaj razred služi za dohvaćanje i zatvaranje {@link EntityManager}. Svaki dretva ima svoj primjerak istog.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
class JPAEMProvider {

    private static final ThreadLocal<LocalData> locals = new ThreadLocal<>();

    /**
     * Dohvaća entity manager od trenutne dretve
     * 
     * @return entity manager
     */
    public static EntityManager getEntityManager() {
        LocalData ldata = locals.get();
        if (ldata == null) {
            ldata = new LocalData();
            ldata.em = JPAEMFProvider.getEmf().createEntityManager();
            ldata.em.getTransaction().begin();
            locals.set(ldata);
        }
        return ldata.em;
    }

    /**
     * Radi commmit na transakciji i zatvara entitiy manager.
     * 
     * @throws DAOException ako ne uspije commit ili zatvaranje
     */
    public static void close() throws DAOException {
        LocalData ldata = locals.get();
        if (ldata == null) {
            return;
        }
        DAOException dex = null;
        try {
            EntityTransaction transaction = ldata.em.getTransaction();
            if (transaction.isActive()) {
                if (!transaction.getRollbackOnly()) {
                    transaction.commit();
                } else {
                    transaction.rollback();
                }
            }
        } catch (Exception ex) {
            dex = new DAOException("Unable to commit transaction.", ex);
        }
        try {
            ldata.em.close();
        } catch (Exception ex) {
            if (dex != null) {
                dex = new DAOException("Unable to close entity manager.", ex);
            }
        }
        locals.remove();
        if (dex != null) throw dex;
    }

    /**
     * Ovaj razred ima pohranjene sve stvari koje su potrebne jednoj dretvi. Zbog trivijalne implementacije, potreban
     * joj je samo {@link EntityManager}.
     * 
     * @author Filip Hrenić
     * @version 1.0
     */
    private static class LocalData {
        EntityManager em;
    }

}
