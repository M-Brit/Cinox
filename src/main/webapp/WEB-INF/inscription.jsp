<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 17/10/2017
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head> <!-- TODO : mettre une favicon -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="../css/inscription.css" rel="stylesheet">

    <title>Inscription</title>
</head>
<body>

<div class="container">

    <form class="form-signin" method="post" action="inscription" >
        <h2 class="form-signin-heading">Rejoignez Cinox</h2>
        <label class="sr-only" for="pseudo" ></label>
        <input class="form-control" type="text" id="pseudo" name="pseudo"  placeholder="Pseudo"
               value="<c:out value="${param.pseudo}"/>" minlength="3" required autofocus>
        <span class="erreur">${erreurs['pseudo']}</span>

        <label class="sr-only" for="prenom" ></label>
        <input class="form-control" type="text" id="prenom" name="prenom"  placeholder="Prénom"
               value="<c:out value="${param.prenom}"/>" required>
        <span class="erreur">${erreurs['prenom']}</span>

        <label for="nom" class="sr-only"></label>
        <input class="form-control" type="text" id="nom" name="nom"  placeholder="Nom"
               value="<c:out value="${param.nom}"/>" required>
        <span class="erreur">${erreurs['nom']}</span>

        <label for="email" class="sr-only"></label>
        <input class="form-control" type="email" id="email" name="email"  placeholder="Email"
               value="<c:out value="${param.email}"/>" required>
        <span class="erreur">${erreurs['email']}</span>

        <label class="sr-only" for="motdepasse" class="sr-only"></label>
        <input type="password" id="motdepasse" name="motdepasse" class="form-control" placeholder="Mot de passe"
               minlength="3" required>
        <span class="erreur">${erreurs['motdepasse']}</span>

        <label class="sr-only" for="confirmation" ></label>
        <input class="form-control" type="password" id="confirmation" name="confirmation"  placeholder="Confirmation"
               minlength="3" required>
        <span class="erreur">${erreurs['confirmation']}</span>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>
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
