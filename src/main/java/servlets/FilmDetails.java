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

public class FilmDetails extends HttpServlet {

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
