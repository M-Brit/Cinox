package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilmSearch extends HttpServlet {

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
