package servlets;

import beans.Utilisateur;
import forms.NotationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.Connexion.ATT_SESSION_USER;

public class Notation extends HttpServlet {
    private static final String VUE = "/WEB-INF/test.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Utilisateur uRecup = (Utilisateur) req.getSession().getAttribute(ATT_SESSION_USER);
        try {
            int idRecup = uRecup.getId();
            switch (action) {
                case "getNote":
                    int note = NotationForm.getNote(idRecup, Integer.valueOf(req.getParameter("idfilm")));
                    resp.setContentType("plain/text");
                    resp.setHeader("Cache-control", "no-cache");
                    resp.getWriter().write("" + note);
                    resp.getWriter().close();
                    break;
                case "addNote":
                    NotationForm.addNote(idRecup,
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
