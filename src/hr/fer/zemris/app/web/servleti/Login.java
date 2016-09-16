package hr.fer.zemris.app.web.servleti;

import hr.fer.zemris.app.dao.DAOProvider;
import hr.fer.zemris.app.model.BlogUser;
import hr.fer.zemris.app.web.forms.LoginForm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is used for logging in.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
@WebServlet("/servleti/login")
@SuppressWarnings("serial")
class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        LoginForm form = new LoginForm();
        form.fillFromRequest(req);

        form.validate();

        if (form.imaGreske()) {
            req.setAttribute("zapis", form);
            req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);

        } else {
            BlogUser user = new BlogUser();
            form.fillUser(user);
            BlogUser korisnik = DAOProvider.getDAO().getUser(user.getNick(), user.getPasswordHash());

            if (korisnik == null) {
                form.dodajGresku("nick", "User with given nick and password doesn't exist.");
                form.setPassword("");
                form.setNick(user.getNick());
                req.setAttribute("zapis", form);
                req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);
                return;
            }

            req.getSession().setAttribute("current.user", korisnik);
            resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
        }
    }
}
