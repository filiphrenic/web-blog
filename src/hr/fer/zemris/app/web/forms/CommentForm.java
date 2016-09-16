package hr.fer.zemris.app.web.forms;

import hr.fer.zemris.app.model.BlogComment;

import javax.servlet.http.HttpServletRequest;

/**
 * Form za dodavanje komentara.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class CommentForm extends AbstractForm {

    private String usersEMail;
    private String message;

    @Override
    public void fillFromRequest(HttpServletRequest req) {
        usersEMail = obradi(req.getParameter("usersEMail"));
        message = obradi(req.getParameter("message"));
    }

    /**
     * Popunjava form iz komentara
     * 
     * @param comment komentar
     */
    public void fillFromComment(BlogComment comment) {
        usersEMail = comment.getUsersEMail();
        message = comment.getMessage();
    }

    @Override
    public void validate() {
        if (message.isEmpty()) {
            errors.put("message", "You must input a comment.");
        }
    }

    /**
     * @return the usersEMail
     */
    public String getUsersEMail() {
        return usersEMail;
    }

    /**
     * @param usersEMail the usersEMail to set
     */
    public void setUsersEMail(String usersEMail) {
        this.usersEMail = usersEMail;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Popunjava komentar iz forma
     * 
     * @param comment komentar za popuniti
     */
    public void fillComment(BlogComment comment) {
        comment.setMessage(message);
        comment.setUsersEMail(usersEMail);
    }

}
