package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Deconnexion extends HttpServlet {
    // TODO : mettre ici la page index.html a la place de google.fr
    public static final String URL_REDIRECTION = "https://www.google.fr";
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = req.getSession();
        session.invalidate();

        /* Redirection vers notre page d'accueil */
        resp.sendRedirect( URL_REDIRECTION ); // TODO ne surtout pas mettre de forwarding ici
    }
}
