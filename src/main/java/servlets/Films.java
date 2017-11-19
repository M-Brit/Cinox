package servlets;

import dao.FilmImpl;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Servlet qui permet de recuperer les films et donc les affichers
 */
public class Films extends HttpServlet {

    /**
     *
     * Methode de servlet qui gère les requetes get du client pour voir les films.
     * @param request request permet de scruter la requete du client afin d'effectuer les traitements (Metier) la recuperation des films .
     * @param response response permet de repondre au client de facons personnalise pour l' affichage des films .
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            FilmImpl filmImpl = new FilmImpl();

            //TODO all or nowPlaying
            //from MongoDB
            JSONArray films =  filmImpl.findAllFilm();



            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            response.getWriter().write(films.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }






}
