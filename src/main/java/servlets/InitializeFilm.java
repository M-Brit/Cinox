package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* TODO: save to MongoDB
* */
public class InitializeFilm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*  System.out.println("Test1 - Http GET request for API Now Playing");
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

        FilmForm themoviedb = new FilmForm();
        FilmImpl filmImpl = new FilmImpl();

        try {
            //now playing
            JSONArray listFilms = themoviedb.nowPlaying();
            System.out.println("nowPlaying=="+ listFilms.length());
            filmImpl.ajoutFilm("nowPlaying", listFilms);

            //up coming
            listFilms = themoviedb.upComing();
            System.out.println("upComing=="+ listFilms.length());
            filmImpl.ajoutFilm("upComing", listFilms);

            //popular
            listFilms = themoviedb.popular();
            System.out.println("popular=="+ listFilms.length());
            filmImpl.ajoutFilm("popular", listFilms);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
