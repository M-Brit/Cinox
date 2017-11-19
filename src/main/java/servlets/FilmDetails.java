package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet qui permet de recuperer les details sur les films et donc de les afficher
 */
public class FilmDetails extends HttpServlet {

    /**
     *
     * Methode de servlet qui gère les requetes post du client pour voir les films et leur detail (video, acteurs, etc...).
     * @param request request permet de scruter la requete du client afin d'effectuer les traitements (Metier) la recuperation des details des films.
     * @param response response permet de repondre au client de facons personnalise pour l' affichage leur details des films.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        FilmForm themoviedb = new FilmForm();
        FilmImpl filmImpl = new FilmImpl();
        try {
            //TODO
            //from API
            //JSONObject jsonObject = themoviedb.getMovieDetails(id);
            //from MongoDB
            JSONObject jsonObject = filmImpl.findFilmById(id);

            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            response.getWriter().write(jsonObject.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
