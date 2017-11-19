package servlets;

import beans.Utilisateur;
import dao.AmisImpl;
import dao.UtilisateurImpl;
import forms.ProfilForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static servlets.Connexion.ATT_SESSION_USER;
/**
 * Servlet qui gère le profil des utilisateurs
 */
public class Profil extends HttpServlet {
    private static final String VUE = "/WEB-INF/profil.jsp";

    /**
     * Methode de servlet qui gère les requetes Get du client pour la gestion du profil utilisateur.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour la gestion du profil utilisateur.
     * @param resp response permet de repondre au client de facons personnalise pour la gestion du profil utilisateur.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Utilisateur uRecup = (Utilisateur) req.getSession().getAttribute(ATT_SESSION_USER);
            int idRecup = uRecup.getId();
            switch (action) {
                case "update":
                    List l = AmisImpl.rechercheAmis(idRecup); // !!!!! session
                    JSONArray array = ProfilForm.rechercheAmisJSON(l);
                    String s = array.toString();
                    resp.setContentType("plain/text");
                    resp.setHeader("Cache-control", "no-cache");
                    resp.getWriter().write(s);
                    resp.getWriter().close();
                    break;
                case "suppr":
                    AmisImpl.supressionAmis(idRecup, Integer.parseInt(req.getParameter("id")));
                    break;
                case "add":
                    List u = UtilisateurImpl.rechercheUtilisateurs(req.getParameter("pseudo"));
                    if (!u.isEmpty()) {
                        int id = ((Utilisateur) u.get(0)).getId();
                        if (id != idRecup) {
                            AmisImpl.ajoutAmi(idRecup, id);
                        }
                    }
                    // TODO CAS IMPOSSIBLE
                    break;
                case "accept":
                    AmisImpl.accepterAmis(idRecup, Integer.parseInt(req.getParameter("id")));
                    break;
                case "desins":
                    AmisImpl.supressionTousAmis(idRecup);
                    UtilisateurImpl.supressionUtilisateur(idRecup);
                    req.getSession().invalidate();
                    break;
                default:
                    System.out.println("Action invalide !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
