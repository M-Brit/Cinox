package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inscription extends HttpServlet {
    private static final String VUE = "/WEB-INF/inscription.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("@1");
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupération des parametres.
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motdepasse");
        String confirmation = req.getParameter("confirmation");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String pseudo = req.getParameter("pseudo");

        Map<String, String> erreurs = new HashMap<>();

        // Validations.
        try {
            validationEmail(email);
        } catch (Exception e) {
            erreurs.put("email", e.getMessage());
        }

        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            erreurs.put("motdepasse", e.getMessage());
        }

        try {
            validationPseudo(pseudo);
        } catch (Exception e) {
            erreurs.put("pseudo", e.getMessage());
        }


        // Stockage du résultat et des messages d'erreur dans l'objet request.
        req.setAttribute("erreurs", erreurs);

        // Transmission de la paire d'objets request/response à notre JSP.
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);

        // Chargement du driver JDBC pour MySQL.
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // Connexion à la base de données.
        String url = "jdbc:mysql://localhost:3306/mydb";
        String utilisateur = "root";
        String mDP = "oWM,yjo-,8C5";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = DriverManager.getConnection(url, utilisateur, mDP);
            // Création de l'objet gérant la requête.
            preparedStatement = connexion.prepareStatement("INSERT INTO Users (idUsers, email, password, nom, prenom, login) VALUES(4, ?, MD5(?), ?, ?, ?);");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);
            preparedStatement.setString(3, nom);
            preparedStatement.setString(4, prenom);
            preparedStatement.setString(5, pseudo);

            int statut = preparedStatement.executeUpdate();

            // test redirection
            // this.getServletContext().getRequestDispatcher(VUE).forward(null, null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connexion != null) {
                try {
                    // Fermeture de la connexion.
                    connexion.close();
                } catch (SQLException ignore) {
                    // Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }


    // Valide l'adresse mail saisie.
    private void validationEmail(String email) throws Exception {
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    // Valide les mots de passe saisis.
    private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    // Valide le pseudo saisi.
    private void validationPseudo(String nom) throws Exception {
        if (nom != null && nom.trim().length() < 3) {
            throw new Exception("Le pseudo doit contenir au moins 3 caractères.");
        }
        // Chargement du driver JDBC pour MySQL.
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // Connexion à la base de données.
        String url = "jdbc:mysql://localhost:3306/mydb";
        String utilisateur = "root";
        String mDP = "oWM,yjo-,8C5";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = DriverManager.getConnection(url, utilisateur, mDP);
            // Création de l'objet gérant la requête.
            preparedStatement = connexion.prepareStatement("SELECT COUNT(*) FROM Users WHERE login=?;");
            preparedStatement.setString(1, nom);

            ResultSet statut = preparedStatement.executeQuery();
            statut.first();
            if (Objects.equals(statut.getString(1), "1")) {
                throw new Exception("Le pseudo est déjà utilisé.");
            }

            // test redirection
            // this.getServletContext().getRequestDispatcher(VUE).forward(null, null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connexion != null) {
                try {
                    // Fermeture de la connexion.
                    connexion.close();
                } catch (SQLException ignore) {
                    // Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}

