<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 17/10/2017
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link href="../inscription.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>

<div class="container">

    <form method="post" action="inscription" class="form-signin">
        <h2 class="form-signin-heading">Rejoignez Cinox</h2>
        <label for="pseudo" class="sr-only"></label>
        <input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo"
               value="<c:out value="${param.pseudo}"/>" required autofocus>
        <span class="erreur">${erreurs['pseudo']}</span>

        <label for="prenom" class="sr-only"></label>
        <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Prénom"
               value="<c:out value="${param.prenom}"/>" required>
        <span class="erreur">${erreurs['prenom']}</span>

        <label for="nom" class="sr-only"></label>
        <input type="text" id="nom" name="nom" class="form-control" placeholder="Nom"
               value="<c:out value="${param.nom}"/>" required>
        <span class="erreur">${erreurs['nom']}</span>

        <label for="email" class="sr-only"></label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Email"
               value="<c:out value="${param.email}"/>" required>
        <span class="erreur">${erreurs['email']}</span>

        <label for="motdepasse" class="sr-only"></label>
        <input type="password" id="motdepasse" name="motdepasse" class="form-control" placeholder="Mot de passe"
               required>
        <span class="erreur">${erreurs['motdepasse']}</span>

        <label for="confirmation" class="sr-only"></label>
        <input type="password" id="confirmation" name="confirmation" class="form-control" placeholder="Confirmation"
               required>
        <span class="erreur">${erreurs['confirmation']}</span>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>
    </form>

</div> <!-- /container -->

</body>
</html>
