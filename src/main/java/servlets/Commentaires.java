package servlets;

import beans.Utilisateur;
import forms.CommentairesForm;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Commentaires extends HttpServlet {

    public static final String ATT_COMMENTAIRESJSON = "commentaires";
    public static final String ATT_FORMCRITIQUE = "formCritique";
    public static final String ATT_SESSION_USER = "sessionUtilisateur"; //TODO a  mettre si besoin
    public static final String VUE = "/WEB-INF/commentaires.jsp";
    //public static final String VUE_FRONTPAGE = "/WEB-INF/critique.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();


        /* Préparation de l'objet formulaire */
 //       CommentairesForm formCommentaire = new CommentairesForm();
 //       JSONArray commentaires = formCommentaire.obtainCommentaires(request);
        //this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        //formCritique.addCritiquesFilm("1","2", "steven est gentil ??");
        /* Affichage de la page des critiques*/
       ///// System.out.println("jsonarray :"+commentaires);
 //       session.setAttribute(ATT_FORMCRITIQUE, formCommentaire); // TODO : voir si utiliser session ou request ou autre
 //       session.setAttribute(ATT_COMMENTAIRESJSON, commentaires);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    //    response.setContentType("application/json");
       /*   response.setContentType("plain/text");
          response.setHeader("Cache-control", "no-cache");
          response.getWriter().print();
          response.getWriter().flush();
          response.getWriter().close();*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("entre ..........");
        String action = request.getParameter("action");

        /* Récupération de la session/userId depuis la requête */
        HttpSession session = request.getSession();
        System.out.println("session=="+ session);
        System.out.println("session=="+ session.getAttribute("ATT_USER"));
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("ATT_USER");
        System.out.println("userId=="+ utilisateur.getId());


        JSONArray  commentaires;
        CommentairesForm formCommentaire = new CommentairesForm();

        switch (action) {

            case "addComment":
                try {
                    String comment = request.getParameter("comment");
                    formCommentaire.commentaireFilm(request);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                commentaires = formCommentaire.obtainCommentaires(request);
                System.out.println("TEST FIN");
                response.setContentType("plain/text");
                response.setHeader("Cache-control", "no-cache");
                response.getWriter().write(commentaires.toString());
                response.getWriter().flush();
                response.getWriter().close();


               // session.setAttribute(ATT_FORMCRITIQUE, formCommentaire); // TODO : voir si utiliser session ou request ou autre ou enlever
                //session.setAttribute(ATT_COMMENTAIRESJSON, commentaires);
                break;
            case "getComment":

                commentaires = formCommentaire.obtainCommentaires(request);
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


        /* Affichage de la page des critiques*/
      /*  response.setContentType("plain/text");
        //resp.setContentType("plain/text");
        response.setHeader("Cache-control", "no-cache");
        response.getWriter().write("test");
        response.getWriter().flush();
        response.getWriter().close();*/
       // this.getServletContext().getRequestDispatcher(VUE).forward(request, response);


    }
}
