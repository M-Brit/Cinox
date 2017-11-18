package servlets;

import forms.NotationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Notation extends HttpServlet {
    private static final String VUE = "/WEB-INF/test.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "getNote":
                int note = NotationForm.getNote(11, Integer.valueOf(req.getParameter("idfilm")));
                resp.setContentType("plain/text");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write("" + note);
                resp.getWriter().close();
                break;
            case "addNote":
                NotationForm.addNote(11,
                        Integer.valueOf(req.getParameter("idfilm")),
                        Integer.valueOf(req.getParameter("note")));
                NotationForm.calculMoyenne(Integer.valueOf(req.getParameter("idfilm")));
                break;
            case "getMoyenne":
                resp.setContentType("plain/text");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write("" + NotationForm.getMoyenne(Integer.valueOf(req.getParameter("idfilm"))));
                resp.getWriter().close();
                break;
            default:
                System.out.println("Notation : Action invalide !");
        }
    }
}
