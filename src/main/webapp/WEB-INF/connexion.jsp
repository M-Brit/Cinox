<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head> <!-- TODO : mettre une favicon -->
        <meta charset="utf-8" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link href="../css/inscription.css" rel="stylesheet">
        <title>Connexion</title>
    </head>
    <body>
    
        <div class="container">
            <form class="form-signin" method="post" action="connexion">
                <h2 class="form-signin-heading">Connexion Cinox</h2>
                <label class="sr-only" for="email" ></label>
                <input class="form-control" type="email" id="email" name="email"  placeholder="Email"
                       value="<c:out value="${param.email}"/>" required>
                <span class="erreur">${erreurs['email']}</span>


                <label class="sr-only" for="motdepasse" ></label>
                <input class="form-control" type="password" id="motdepasse" name="motdepasse"  placeholder="Mot de passe"
                       minlength="3" required>
                <span class="erreur">${erreurs['motdepasse']}</span>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>

                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

                    <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                </c:if>

            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
crossorigin="anonymous"></script>
    </body>
</html>