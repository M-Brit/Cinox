<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <!-- <link rel="icon" href="../../../../favicon.ico"> -->

    <title>Cinox</title>
    <link rel="icon" href="../../images/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">


    <!-- Custom styles for this template -->
    <link href="css/jumbotron.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Cinox</a>


    <div class="collapse navbar-collapse" id="navbarsExampleDefault">

        <form class="form-inline my-2 my-lg-0" method="post" action="connexion">
            <!-- <input class="form-control mr-sm-2" type="text" placeholder="Pseudo" > -->


            <label class="sr-only" for="pseudo"></label>
            <input class="form-control mr-sm-2" type="text" id="pseudo" name="pseudo" placeholder="Pseudo"
                   value="<c:out value="${utilsateur.pseudo}"/>" minlength="3" required>
            <span class="erreur">${erreurs['pseudo']}</span>


            <!-- <input class="form-control mr-sm-2" type="password" placeholder="Password" > -->

            <label class="sr-only" for="motdepasse"></label>
            <input class="form-control mr-sm-2" type="password" id="motdepasse" name="motdepasse"
                   placeholder="Mot de passe"
                   minlength="3" required/>
            <span class="erreur">${erreurs['motdepasse']}</span>

            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>

        </form>
        <a class="btn btn-outline-warning " href="inscription"  type="warning" role="button">Sign up</a>
    </div>

</nav>

<main role="main">

    <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
    <!-- TODO : a enlever juste pour le test -->
    <%-- Vérification de la présence d'un objet utilisateur en session --%>
    <c:if test="${!empty sessionScope.sessionUtilisateur}">
        <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
        <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.pseudo}</p>
    </c:if>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Bienvenus sur Cinox!</h1>
            <p>Notre application a pour but de vous permettre de voir la liste des films à l'affiche actuellement au cinéma. <br/>Mais aussi des films qui vont sortir prochainement. Vous pourrez voir les détails des films et leurs bandes annonces. Enfin vous pourrez créer des événements afin d'aller au cinéma avec vos amis. </p>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
            <img src = "../image"/>
        </div>
    </div>



</main>



<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
