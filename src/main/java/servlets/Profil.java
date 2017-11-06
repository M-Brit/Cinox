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
        //String s = "[{ \"id\" : \"1\", \"pseudo\" : \"Dark75\", \"prenom\" : \"Jean\", \"nom\" : \"Michel\"}," +
        //        "{ \"id\" : \"2\", \"pseudo\" : \"White75\", \"prenom\" : \"Jean\", \"nom\" : \"Michel\"}]";


        List l = AmisImpl.rechercheAmis(11);
        JSONArray array = ProfilForm.rechercheAmisJSON(l);
        System.out.println(l.get(0).toString() + " - " + l.get(1).toString());
        String s = array.toString();
        resp.setContentType("plain/text");
        resp.setHeader("Cache-control", "no-cache");
        resp.getWriter().write(s);
        resp.getWriter().close();
    }
}
