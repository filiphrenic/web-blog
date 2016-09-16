package hr.fer.zemris.app.web.servleti;

import hr.fer.zemris.app.dao.DAOProvider;
import hr.fer.zemris.app.model.BlogUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/servleti/main", "/index.jsp" })
class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BlogUser> users = DAOProvider.getDAO().getAllUsers();
        req.setAttribute("authors", users);
        req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);
    }

}
