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

public class Profil extends HttpServlet {
    private static final String VUE = "/WEB-INF/profil.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO GERER SESSION
        String action = req.getParameter("action");
        switch (action) {
            case "update":
                List l = AmisImpl.rechercheAmis(11); // !!!!! session
                JSONArray array = ProfilForm.rechercheAmisJSON(l);
                String s = array.toString();
                resp.setContentType("plain/text");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write(s);
                resp.getWriter().close();
                break;
            case "suppr":
                AmisImpl.supressionAmis(11, Integer.parseInt(req.getParameter("id")));
                break;
            case "add":
                List u = UtilisateurImpl.rechercheUtilisateurs(req.getParameter("pseudo"));
                if (!u.isEmpty()) {
                    int id = ((Utilisateur) u.get(0)).getId();
                    if (id != 11) {
                        AmisImpl.ajoutAmi(11, id);
                    }
                }
                // TODO CAS IMPOSSIBLE
                break;
            case "accept":
                AmisImpl.accepterAmis(11, Integer.parseInt(req.getParameter("id")));
                break;
            case "desins":
                AmisImpl.supressionTousAmis(14);
                UtilisateurImpl.supressionUtilisateur(14);
                break;
            default:
                System.out.println("Action invalide !");
        }
    }
}
