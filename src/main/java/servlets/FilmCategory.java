package servlets;

import dao.FilmImpl;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet qui permet de recuperer les films en foncion d'une category et donc les affichers
 */
public class FilmCategory extends HttpServlet {
    /**
     *
     * Methode de servlet qui gère les requetes get du client pour voir les films en fonction des categorie( action, aventures etc...).
     * @param request request permet de scruter la requete du client afin d'effectuer les traitements (Metier) la recuperation des films par categorie.
     * @param response response permet de repondre au client de facons personnalise pour l' affichage des films par categorie .
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String categoryId = request.getParameter("categoryId");
        FilmImpl filmImpl = new FilmImpl();
        try {

            JSONArray films =  filmImpl.getByCategory(Integer.valueOf(categoryId));

            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            response.getWriter().write(films.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
