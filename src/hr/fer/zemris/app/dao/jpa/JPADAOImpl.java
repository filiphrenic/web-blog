package hr.fer.zemris.app.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import hr.fer.zemris.app.dao.DAO;
import hr.fer.zemris.app.dao.DAOException;
import hr.fer.zemris.app.model.BlogComment;
import hr.fer.zemris.app.model.BlogEntry;
import hr.fer.zemris.app.model.BlogUser;

/**
 * Implementacija podatkovnog sloja koji koristi JPA
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class JPADAOImpl implements DAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<BlogUser> getAllUsers() throws DAOException {
        return (List<BlogUser>) JPAEMProvider.getEntityManager().createQuery("select u from BlogUser u")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BlogEntry> getBlogEntries(BlogUser user) throws DAOException {
        return (List<BlogEntry>) JPAEMProvider.getEntityManager().createQuery(
                "select e from BlogEntry as e where e.creator=:creator").setParameter("creator", user).getResultList();
    }

    @Override
    public BlogEntry getBlogEntry(Long id) throws DAOException {
        return JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlogUser getUser(String nick) throws DAOException {
        List<BlogUser> users = (List<BlogUser>) JPAEMProvider.getEntityManager().createQuery(
                "select b from BlogUser as b where b.nick=:nick").setParameter("nick", nick).getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlogUser getUser(String nick, String passwordHash) throws DAOException {
        List<BlogUser> users = (List<BlogUser>) JPAEMProvider.getEntityManager().createQuery(
                "select b from BlogUser as b where b.nick=:nick and b.passwordHash=:passwordHash").setParameter("nick",
                nick).setParameter("passwordHash", passwordHash).getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void saveComment(BlogComment comment) throws DAOException {
        EntityManager em = JPAEMProvider.getEntityManager();

        if (comment.getId() == null) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }

    }

    @Override
    public void saveEntry(BlogEntry entry) throws DAOException {
        EntityManager em = JPAEMProvider.getEntityManager();

        if (entry.getId() == null) {
            em.persist(entry);
        } else {
            em.merge(entry);
        }

    }

    @Override
    public void saveUser(BlogUser user) throws DAOException {
        EntityManager em = JPAEMProvider.getEntityManager();

        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }

    }

}
