package servlets;

import api.MovieApi;
import beans.Movie;
import forms.FilmForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String movieName=req.getParameter("titleFilm");
        System.out.println("title="+ movieName);

        FilmForm api=new FilmForm();
        try {
            JSONArray list = api.getMovieWithKeywords(movieName);
            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            System.out.println("liste ->: "+list.toString());
            response.getWriter().write(list.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String movieName=req.getParameter("titleFilm");
        System.out.println("title="+ movieName);

        FilmForm api=new FilmForm();
        try {
            JSONArray list = api.getMovieWithKeywords(movieName);
            response.setContentType("plain/text");
            response.setHeader("Cache-control", "no-cache");
            System.out.println("liste : "+list.toString());
            response.getWriter().write(list.toString());
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
