package servlets;

import dao.EventsImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletNewEvent", urlPatterns = {"/myServletNewEvent"})
public class ServletNewEvent extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf8");

        JSONObject jsonObj = new JSONObject(request.getParameter("para"));

        /* insert new event into data base */
        int rdvUser = jsonObj.optInt("rdvUser", 0);
        String rdvMovie = jsonObj.optString("rdvMovie", "N/A");
        String rdvCinema = jsonObj.optString("rdvCinema", "N/A");
        String rdvAdress = jsonObj.optString("rdvAdress", "N/A");
        String rdvDate = jsonObj.optString("rdvDate", "N/A");
        String rdvTime = jsonObj.optString("rdvTime", "N/A");

        EventsImpl.addEvent(rdvMovie, rdvCinema, rdvAdress, rdvDate, rdvTime, rdvUser, 1);

        System.out.println("New event for user : " + rdvUser);
    }
}
