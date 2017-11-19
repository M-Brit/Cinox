package servlets;

import dao.FilmImpl;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilmCategory extends HttpServlet {
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
