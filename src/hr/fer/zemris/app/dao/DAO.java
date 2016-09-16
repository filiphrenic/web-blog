package hr.fer.zemris.app.dao;

import java.util.List;

import hr.fer.zemris.app.model.BlogComment;
import hr.fer.zemris.app.model.BlogEntry;
import hr.fer.zemris.app.model.BlogUser;

/**
 * Sučelje koje predstavlja podatkovni sloj.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public interface DAO {

    /**
     * Dohvaća entry sa zadanim <code>id</code>-em. Ako takav entry ne postoji,
     * vraća <code>null</code>.
     * 
     * @param id ključ zapisa
     * @return entry ili <code>null</code> ako entry ne postoji
     * @throws DAOException ako dođe do pogreške pri dohvatu podataka
     */
    BlogEntry getBlogEntry(Long id) throws DAOException;

    /**
     * Vraća sve blog entriese od zadanog usera.
     * 
     * @param user user čije entriese hoćemo
     * @return listu entriesa
     * @throws DAOException ako je greška u dohvatu
     */
    List<BlogEntry> getBlogEntries(BlogUser user) throws DAOException;

    /**
     * Dohvaća usera sa zadanim nickom i passwordHashom. Ako takav user ne postoji, vraća <code>null</code>.
     * 
     * @param nick user nick
     * @param passwordHash user's password hash
     * @return user or <code>null</code>
     * @throws DAOException ako dođe do greške pri dohvatu
     */
    BlogUser getUser(String nick, String passwordHash) throws DAOException;

    /**
     * Dohvaća usera s zadanim nickom, ako takav ne postoji vraća <code>null</code>
     * 
     * @param nick nick
     * @return user
     * @throws DAOException ako je došlo do pogreške u dohvatu
     */
    BlogUser getUser(String nick) throws DAOException;

    /**
     * Dohvaća sve usere u bazi.
     * 
     * @return lista usera
     * @throws DAOException ako dođe do greške pri dohvatu
     */
    List<BlogUser> getAllUsers() throws DAOException;

    /**
     * Sprema usera u bazu podataka Insert/update
     * 
     * @param user user kojeg treba spremiti
     * @throws DAOException ako dođe do greške pri insert/updateu
     */
    void saveUser(BlogUser user) throws DAOException;

    /**
     * Sprema entry u bazu podataka. Insert/update
     * 
     * @param entry koji treba spremiti
     * @throws DAOException
     */
    void saveEntry(BlogEntry entry) throws DAOException;

    /**
     * Sprema komentar u bazu podataka. Insert/update
     * 
     * @param comment komntar koji treba spremiti
     * @throws DAOException
     */
    void saveComment(BlogComment comment) throws DAOException;

}
