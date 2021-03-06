package servlets;

import dao.UtilisateurImpl;
import forms.InscriptionForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
/**
 * Servlet qui gère les inscriptions des utilisateurs
 */
public class Inscription extends HttpServlet {
    private static final String VUE = "/WEB-INF/inscription.jsp";
    private static final String VUE_ACCUEIL = "/index.jsp";

    /**
     *  Methode de servlet qui gère les requetes Get du client pour l'inscription.
     * @param req req permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour l'inscription.
     * @param resp resp permet de repondre au client de facons personnalise pour l'inscription.
     * @throws ServletException
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    /**
     * Methode de servlet qui gère les requetes Post du client pour l'inscription.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) pour l'inscription.
     * @param resp response permet de repondre au client de facons personnalise pour l'inscription.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupération des parametres.
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motdepasse");
        String confirmation = req.getParameter("confirmation");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String pseudo = req.getParameter("pseudo");

        Map<String, String> erreurs = InscriptionForm.validation(pseudo, email, motDePasse, confirmation);

        if (!erreurs.isEmpty()) {
            // Stockage du résultat et des messages d'erreur dans l'objet request.
            req.setAttribute("erreurs", erreurs);

            // Transmission de la paire d'objets request/response à notre JSP.
            this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
        } else {
            UtilisateurImpl.ajoutUtilisateur(nom, prenom, pseudo, motDePasse, email);
            this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
            //resp.sendRedirect(req.getContextPath() + VUE_ACCUEIL);
        }
    }
}

