package hr.fer.zemris.app.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Ovaj razred služi kao provider za {@link EntityManagerFactory}.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class JPAEMFProvider {

    private static EntityManagerFactory emf;

    /**
     * @return trenutni {@link EntityManagerFactory}
     */
    public static EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * Postavlja novi {@link EntityManagerFactory}
     * 
     * @param emf novi entity manager factory
     */
    public static void setEmf(EntityManagerFactory emf) {
        JPAEMFProvider.emf = emf;
    }
}
