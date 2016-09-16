package hr.fer.zemris.app.web.forms;

import hr.fer.zemris.app.model.BlogUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Form used for logging in.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class LoginForm extends AbstractForm {

    private String nick;
    private String password;

    /**
     * Fills the user with data provided through the form
     * 
     * @param user user to fill
     */
    public void fillUser(BlogUser user) {
        user.setNick(nick);
        user.setPassword(password);
    }

    @Override
    public void fillFromRequest(HttpServletRequest req) {
        nick = obradi(req.getParameter("nick"));
        password = obradi(req.getParameter("password"));
    }

    /**
     * Popunjava formular iz usera
     * 
     * @param user user
     */
    public void fillFromUser(BlogUser user) {
        nick = obradi(user.getNick());
        password = obradi(user.getPassword());
    }

    @Override
    public void validate() {
        if (nick.isEmpty()) {
            errors.put("nick", "Must provide your nick.");
        }
        if (password.isEmpty()) {
            errors.put("password", "Must provide your password.");
        }
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
