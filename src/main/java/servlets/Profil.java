package servlets;

import dao.AmisImpl;
import forms.ProfilForm;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Profil extends HttpServlet {
    private static final String VUE = "/WEB-INF/profil.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO GERER SESSION
        String action = req.getParameter("action");
        switch (action) {
            case "update":
                List l = AmisImpl.rechercheAmis(11); // !!!!! session
                JSONArray array = ProfilForm.rechercheAmisJSON(l);
                String s = array.toString();
                resp.setContentType("plain/text");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write(s);
                resp.getWriter().close();
                break;
            case "suppr":
                AmisImpl.supressionAmis(11, Integer.parseInt(req.getParameter("id")));
                break;
            default:
                System.out.println("Action invalide !");
        }
    }
}
