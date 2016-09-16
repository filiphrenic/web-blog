package hr.fer.zemris.app.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used for sending redirections to the error.jsp
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
final class ErrorServlet {

    /**
     * Can't be created.
     */
    private ErrorServlet() {
    }

    /**
     * Šalje poruku na ispis stranici Error.jsp
     * 
     * @param req
     * @param resp
     * @param message poruka koju treba ispisati
     * @throws ServletException
     * @throws IOException
     */
    public static void send(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException,
            IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/pages/Error.jsp").forward(req, resp);
    }

}
