package hr.fer.zemris.app.web.forms;

import hr.fer.zemris.app.model.BlogUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Form koji služi za registraciju usera.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class RegistrationForm extends AbstractForm {

    private String firstName;
    private String lastName;
    private String email;
    private String nick;
    private String password;

    /**
     * Popunjava podatke u user-a pomoću ovog forma.
     * 
     * @param user user za popuniti
     */
    public void fillUser(BlogUser user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setNick(nick);
        user.setPassword(password);
    }

    /**
     * Fills the form using the request
     * 
     * @param req request needed to fill the form
     */
    @Override
    public void fillFromRequest(HttpServletRequest req) {
        firstName = obradi(req.getParameter("firstName"));
        lastName = obradi(req.getParameter("lastName"));
        email = obradi(req.getParameter("email"));
        nick = obradi(req.getParameter("nick"));
        password = obradi(req.getParameter("password"));
    }

    /**
     * Fills the form using user's data
     * 
     * @param user user
     */
    public void fillFromUser(BlogUser user) {
        firstName = obradi(user.getFirstName());
        lastName = obradi(user.getLastName());
        email = obradi(user.getEmail());
        nick = obradi(user.getNick());
        password = obradi(user.getPassword());
    }

    @Override
    public void validate() {

        errors.clear();

        if (firstName.isEmpty()) {
            errors.put("firstName", "Must provide first name.");
        }
        if (lastName.isEmpty()) {
            errors.put("lastName", "Must provide last name.");
        }
        if (email.isEmpty()) {
            errors.put("email", "Must provide e-mail name.");
        } else {
            if (!email.contains("@")) {
                errors.put("email", "E-mail must contain a '@' sign");
            }
        }
        if (nick.isEmpty()) {
            errors.put("nick", "Must provide nickname.");
        }
        if (password.isEmpty()) {
            errors.put("password", "Must provide password");
        }
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
    }

}
