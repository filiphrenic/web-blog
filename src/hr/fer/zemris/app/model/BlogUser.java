package hr.fer.zemris.app.model;

import hr.fer.zemris.app.hash.ShaDigester;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Ovaj razred opisuje jednog blog usera.
 * Svaki user ima ime, prezime, nick, password, email i svoje blog entries.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
@Entity
@Table(name = "blog_users")
@Cacheable(true)
public class BlogUser {

    // ================================================================================================

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 40)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 20)
    private String nick;

    @Transient
    private String password;

    @Column(nullable = false, length = 40)
    private String passwordHash;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OrderBy("createdOn")
    private List<BlogEntry> blogEntries;

    // ================================================================================================

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
        this.passwordHash = new ShaDigester(password).calculateDigest().getDigest();
    }

    /**
     * @return the blogEntries
     */
    public List<BlogEntry> getBlogEntries() {
        return blogEntries;
    }

    /**
     * @param blogEntries the blogEntries to set
     */
    public void setBlogEntries(List<BlogEntry> blogEntries) {
        this.blogEntries = blogEntries;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BlogUser other = (BlogUser) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }

    @Override
    public String toString() {
        return nick;
    }

}
