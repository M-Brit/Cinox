package servlets;

import beans.Utilisateur;
import forms.NotationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.Connexion.ATT_SESSION_USER;

/**
 * Servlet qui gère les notations de film
 */
public class Notation extends HttpServlet {
    private static final String VUE = "/WEB-INF/test.html";

    /**
     * Methode de servlet qui gère les requetes Get du client pour la notation.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour la notation.
     * @param resp response permet de repondre au client de facons personnalise pour la notation.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }
    /**
     * Methode de servlet qui gère les requetes Post du client pour la notation.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour la notation.
     * @param resp response permet de repondre au client de facons personnalise pour la notation.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Utilisateur uRecup = (Utilisateur) req.getSession().getAttribute(ATT_SESSION_USER);
        try {
            int idRecup = uRecup.getId();
            switch (action) {
                case "getNote":
                    int note = NotationForm.getNote(idRecup, Integer.valueOf(req.getParameter("idfilm")));
                    resp.setContentType("plain/text");
                    resp.setHeader("Cache-control", "no-cache");
                    resp.getWriter().write("" + note);
                    resp.getWriter().close();
                    break;
                case "addNote":
                    NotationForm.addNote(idRecup,
                            Integer.valueOf(req.getParameter("idfilm")),
                            Integer.valueOf(req.getParameter("note")));
                    NotationForm.calculMoyenne(Integer.valueOf(req.getParameter("idfilm")));
                    break;
                case "getMoyenne":
                    resp.setContentType("plain/text");
                    resp.setHeader("Cache-control", "no-cache");
                    resp.getWriter().write("" + NotationForm.getMoyenne(Integer.valueOf(req.getParameter("idfilm"))));
                    resp.getWriter().close();
                    break;
                default:
                    System.out.println("Notation : Action invalide !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
