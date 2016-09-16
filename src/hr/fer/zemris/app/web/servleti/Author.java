package hr.fer.zemris.app.web.servleti;

import hr.fer.zemris.app.dao.DAO;
import hr.fer.zemris.app.dao.DAOProvider;
import hr.fer.zemris.app.model.BlogComment;
import hr.fer.zemris.app.model.BlogEntry;
import hr.fer.zemris.app.model.BlogUser;
import hr.fer.zemris.app.web.forms.CommentForm;
import hr.fer.zemris.app.web.forms.EntryForm;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/author/*")
@SuppressWarnings("serial")
class Author extends HttpServlet {

    private static final int NICK_INDEX = 0;
    private static final int ENTRY_INDEX = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        String[] pathInfo = req.getPathInfo().substring(1).split("/");
        int len = pathInfo.length;

        if (len == 0) {
            ErrorServlet.send(req, resp, "Link doesn't contain all needed parts");
            return;
        }

        BlogUser author = DAOProvider.getDAO().getUser(pathInfo[NICK_INDEX]);

        if (author == null) {
            ErrorServlet.send(req, resp, "User with given nick doesn't exist.");
            return;
        }

        Boolean sameUser = false;
        if (author.equals(req.getSession().getAttribute("current.user"))) {
            sameUser = true;
        }

        if (len == 1) {

            List<BlogEntry> entries = DAOProvider.getDAO().getBlogEntries(author);

            req.setAttribute("entries", entries);
            req.setAttribute("user", author);
            req.setAttribute("sameUser", sameUser);

            req.getRequestDispatcher("/WEB-INF/pages/ShowEntries.jsp").forward(req, resp);
            return;
        }

        // len je 2
        // ili imam authors/nick/eid ili author/nick/eid?del=?
        // ili authors/nick/new ili edit

        String param = pathInfo[ENTRY_INDEX];

        if (param.equals("new") || param.startsWith("edit")) {
            if (!sameUser) {
                ErrorServlet.send(req, resp, "You do not have privleges for this action.");
            }
        }

        if (param.equals("new")) {
            newBlogEntry(req, resp);
        } else if (param.startsWith("edit")) {
            editBlogEntry(req, resp);
        } else {
            showEntry(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        // ovo se poziva nakon dodavanja novog / uređivanja postojećeg entrya ili je dodan komentar

        String method = req.getParameter("metoda");

        if (method.equalsIgnoreCase("objavi")) {
            postNewComment(req, resp);
            return;
        }

        // ako nije comment, onda je entry

        String nick = req.getPathInfo().substring(1).split("/")[NICK_INDEX];

        if (method.equalsIgnoreCase("odustani")) {
            resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick);
            return;
        }

        EntryForm form = new EntryForm();
        form.fillFromRequest(req);

        form.validate();
        if (form.imaGreske()) {
            req.setAttribute("zapis", form);
            req.setAttribute("user", DAOProvider.getDAO().getUser(nick));
            String tip = req.getParameter("tip");
            req.setAttribute("type", tip);
            req.getRequestDispatcher("/WEB-INF/pages/NewEntry.jsp").forward(req, resp);
            return;
        }

        BlogUser creator = DAOProvider.getDAO().getUser(nick);

        BlogEntry entry = new BlogEntry();
        form.fillEntry(entry);

        if (entry.getId() == null) {
            entry.setCreatedAt(new Date());
            entry.setCreator(creator);
        } else {
            entry = DAOProvider.getDAO().getBlogEntry(entry.getId());
            form.fillEntry(entry);
            entry.setLastModifiedAt(new Date());
        }

        DAOProvider.getDAO().saveEntry(entry);

        resp.sendRedirect(req.getContextPath() + "/servleti/author/" + creator.getNick());
    }

    /**
     * Posta novi komentar ako su dana oba parametra: i email i sam komentar
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void postNewComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentForm form = new CommentForm();
        form.fillFromRequest(req);
        if (form.getUsersEMail().isEmpty()) {
            form.setUsersEMail("Anoniman");
        }

        form.validate();

        if (form.imaGreske()) {
            pripremiRequestZaPrikazEntryja(req, resp, false);
            req.getRequestDispatcher("/WEB-INF/pages/ShowEntry.jsp").forward(req, resp);
            return;
        }

        String eid = req.getPathInfo().substring(1).split("/")[ENTRY_INDEX];
        Long id;
        try {
            id = Long.valueOf(eid);
        } catch (NullPointerException | NumberFormatException e) {
            ErrorServlet.send(req, resp, "Provided blog entry id wasn't a number.");
            return;
        }

        // spremi novi komentar
        BlogEntry entry = DAOProvider.getDAO().getBlogEntry(id);
        BlogComment comment = new BlogComment();
        form.fillComment(comment);

        comment.setBlogEntry(entry);
        comment.setPostedOn(new Date());
        DAOProvider.getDAO().saveComment(comment);

        pripremiRequestZaPrikazEntryja(req, resp, false);
        req.getRequestDispatcher("/WEB-INF/pages/ShowEntry.jsp").forward(req, resp);

    }

    /**
     * Stvaranje novog bloga
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void newBlogEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("type", "new");
        obradiEntry(req, resp, new BlogEntry());
    }

    /**
     * Obrada starog bloga
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editBlogEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e) {
            ErrorServlet.send(req, resp, "Provided blog entry id wasn't a number.");
            return;
        }

        BlogEntry entry = DAOProvider.getDAO().getBlogEntry(id);
        req.setAttribute("type", "edit?id=" + id.toString());
        obradiEntry(req, resp, entry);
    }

    /**
     * Metoda služi za pozivanje NewEntry.jsp, priprema sve parametre za prikaz
     * 
     * @param req
     * @param resp
     * @param entry entry koji služi da se popuni form
     * @throws ServletException
     * @throws IOException
     */
    private void obradiEntry(HttpServletRequest req, HttpServletResponse resp, BlogEntry entry)
            throws ServletException, IOException {
        EntryForm form = new EntryForm();
        form.fillFromEntry(entry);
        String nick = req.getPathInfo().substring(1).split("/")[NICK_INDEX];
        BlogUser user = DAOProvider.getDAO().getUser(nick);

        req.setAttribute("user", user);
        req.setAttribute("zapis", form);
        req.getRequestDispatcher("/WEB-INF/pages/NewEntry.jsp").forward(req, resp);
    }

    /**
     * Prikazivanje entrija
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pripremiRequestZaPrikazEntryja(req, resp, false);
        req.getRequestDispatcher("/WEB-INF/pages/ShowEntry.jsp").forward(req, resp);
    }

    /**
     * Priprema request za prikaz entry-ja. Stavlja mu atribute sameUser, user, entry, comments i zapis
     * 
     * @param req request
     * @param resp respones
     * @throws ServletException
     * @throws IOException
     */
    private void pripremiRequestZaPrikazEntryja(HttpServletRequest req, HttpServletResponse resp, boolean fromRequest)
            throws ServletException, IOException {
        String[] pInfo = req.getPathInfo().substring(1).split("/");
        String nick = pInfo[NICK_INDEX];
        String eid = fromRequest ? req.getParameter("id") : pInfo[ENTRY_INDEX];

        Long id;
        try {
            id = Long.valueOf(eid);
        } catch (NullPointerException | NumberFormatException e) {
            ErrorServlet.send(req, resp, "Provided blog entry id wasn't a number.");
            return;
        }

        DAO dao = DAOProvider.getDAO();

        BlogUser user = dao.getUser(nick);
        if (user == null) {
            ErrorServlet.send(req, resp, "User with nick " + nick + " doesn't exist.");
            return;
        }
        boolean sameUser = false;
        if (user.equals(req.getSession().getAttribute("current.user"))) {
            sameUser = true;
        }

        BlogEntry entry = dao.getBlogEntry(id);
        if (entry == null) {
            ErrorServlet.send(req, resp, "Blog entry with id " + id.toString() + " doesn't exist.");
            return;
        }

        List<BlogComment> comments = entry.getComments();

        CommentForm form = new CommentForm();
        form.fillComment(new BlogComment());

        req.setAttribute("sameUser", sameUser);
        req.setAttribute("user", user);
        req.setAttribute("entry", entry);
        req.setAttribute("comments", comments);
        req.setAttribute("zapis", form);

    }

}
