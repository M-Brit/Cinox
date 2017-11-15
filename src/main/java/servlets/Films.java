package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Films extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FilmForm themoviedb = new FilmForm();

        try {
           JSONArray listFilms = themoviedb.nowPlaying();

         /*   System.out.println("Test1 - Http GET request for API Now Playing");
            themoviedb.nowPlaying();
            System.out.println("Test2 - Http GET request for API Up Coming");
            themoviedb.upComing();
            System.out.println("Test3 - Http GET request for API Top Rated");
            themoviedb.topRated();
            System.out.println("Test4 - Http GET request for API Search Movies");
            themoviedb.getMovieWithKeywords("Star Wars");
            System.out.println("Test5 - Http GET request for API Movies Details");
            themoviedb.getMovieDetails("1891");
            System.out.println("Test6 - Http GET request for API Now Playing");
            JSONArray listFilms = themoviedb.getMovieByTypeId(28);

            */

            FilmImpl test = new FilmImpl();
            test.ajoutFilm(listFilms);

            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            response.getWriter().write(listFilms.toString());
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
