package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Servlet qui gère les deconnexions utilisateurs
 */
public class Deconnexion extends HttpServlet {
    // TODO : mettre ici la page index.html a la place de google.fr
    public static final String URL_REDIRECTION = "/index.jsp";

    /**
     *
     * Methode de servlet qui gère les requetes Get du client pour la deconnexions.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour la deconnexions.
     * @param resp response permet de repondre au client de facons personnalise pour la deconnexions.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Récupération et destruction de la session en cours */
        HttpSession session = req.getSession();
        session.invalidate();

        /* Redirection vers notre page d'accueil */
        resp.sendRedirect(req.getContextPath() + URL_REDIRECTION ); // TODO ne surtout pas mettre de forwarding ici
                                           // TODO qd l'url changera vers la page de notre dossier mettre : request.getContextPath() + URL_REDIRECTION
    }
}
