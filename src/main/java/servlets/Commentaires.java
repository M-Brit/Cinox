package servlets;

import beans.Utilisateur;
import forms.CommentairesForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static servlets.Connexion.ATT_SESSION_USER;

/**
 * Servlet qui gère tous ce qui est en rapport avec les commentaires de films
 */
public class Commentaires extends HttpServlet {

    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE = "/WEB-INF/commentaires.jsp";

    /**
     * Methode de servlet qui gère les requetes Get du client .
     * @param request request permet de scruter la requete du client afin d'effectuer les traitements (Metier).
     * @param response response permet de repondre au client de facons personnalise
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    /**
     * Methode de servlet qui gère les requetes Post du client .
     * @param request request permet de scruter la requete du client afin d'effectuer les traitements (Metier).
     * @param response response permet de repondre au client de facons personnalise
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idFilm = request.getParameter("idFilm");

        /* Récupération de la session/userId depuis la requête grace au cookie session*/
        Utilisateur uRecup = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);

        String idUser =""+uRecup.getId();
        String userName =""+uRecup.getPrenom()+ " "+ uRecup.getNom();

        JSONArray commentaires;
        CommentairesForm formCommentaire = new CommentairesForm();

        switch (action) {

            case "addComment":
                String comment = request.getParameter("comment");
                commentaires = formCommentaire.commentaireFilm(idFilm, idUser, userName, comment);

                response.setContentType("plain/text");
                response.setHeader("Cache-control", "no-cache");
                response.getWriter().write(commentaires.toString());
                response.getWriter().flush();
                response.getWriter().close();

                break;
            case "getComment":
                commentaires = formCommentaire.obtainCommentaires(idFilm);
                response.setContentType("plain/text");
                response.setHeader("Cache-control", "no-cache");
                response.getWriter().write(commentaires.toString());
                response.getWriter().flush();
                response.getWriter().close();

                break; // TODO a enlever si pas besoin
            default:
                System.out.println(" pas bon !!!!"); // TODO a bien gerer
                break;
        }

    }
}
