package servlets;

import api.MovieApi;
import beans.Movie;
import forms.FilmForm;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Films extends HttpServlet {

    JSONObject jsonObject;
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

            */

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
