package servlets;

import api.MovieApi;
import beans.Movie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String movieName=req.getParameter("title");
        System.out.println("title="+ movieName);

        MovieApi api=new MovieApi();
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            movieList = api.getMovieWithKeywords(movieName);
            System.out.println("size="+ movieList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("movieList", movieList);
        req.getRequestDispatcher("../movieList.jsp").forward(req, res);
    }

}
