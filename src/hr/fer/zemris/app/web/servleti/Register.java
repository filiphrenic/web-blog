package hr.fer.zemris.app.web.servleti;

import hr.fer.zemris.app.dao.DAOProvider;
import hr.fer.zemris.app.model.BlogUser;
import hr.fer.zemris.app.web.forms.RegistrationForm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/register")
@SuppressWarnings("serial")
class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationForm form = new RegistrationForm();
        form.fillFromUser(new BlogUser());

        req.setAttribute("zapis", form);
        req.getRequestDispatcher("/WEB-INF/pages/Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String metoda = req.getParameter("metoda");

        if ("Odustani".equals(metoda)) {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
            return;
        }

        RegistrationForm form = new RegistrationForm();
        form.fillFromRequest(req);

        form.validate();

        if (form.imaGreske()) {
            req.setAttribute("zapis", form);
            req.getRequestDispatcher("/WEB-INF/pages/Registration.jsp").forward(req, resp);

        } else {
            BlogUser user = new BlogUser();
            form.fillUser(user);

            BlogUser user2 = DAOProvider.getDAO().getUser(user.getNick());
            if (user2 != null) {
                // postoji već takav nick
                form.dodajGresku("nick", "User with that nick already exists. Please chose another.");
                req.setAttribute("zapis", form);
                req.getRequestDispatcher("/WEB-INF/pages/Registration.jsp").forward(req, resp);
                return;
            }

            DAOProvider.getDAO().saveUser(user);

            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/pages/RegSuccess.jsp").forward(req, resp);
        }

    }

}
