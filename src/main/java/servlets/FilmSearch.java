package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet qui permet de recuperer les films en fonctions d'une recherche par mots cles et donc les affichers
 */
public class FilmSearch extends HttpServlet {
    /**
     *
     * Methode de servlet qui gère les requetes get du client pour voir les films en fonction d'une recherche par mots cles.
     * @param req request permet de scruter la requete du client afin d'effectuer les traitements (Metier) la recuperation des films en fonction d'une recherche par mots cles.
     * @param response response permet de repondre au client de facons personnalise pour l' affichage des films en fonction d'une recherche par mots cles.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String movieName=req.getParameter("titleFilm");

        //from API
        //FilmForm api=new FilmForm();
        //JSONArray list = api.getMovieWithKeywords(movieName);

        //from MongoDB
        FilmImpl filmImpl = new FilmImpl();
        try {
            JSONArray list = filmImpl.searchByKeyword(movieName);

            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            response.getWriter().write(list.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
