<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 04/11/2017
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/profil.css" rel="stylesheet">

    <title>Profil</title>
</head>


<body>


<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="#">Cinox</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Populaire</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Mieux notés</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Prochainement</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Actuellement</a>
            </li>

        </ul>
    </div>
</nav>
<br>

<div class="container">
    <h1>Ajout d'un ami</h1>
    <div class="form-inline">
        <input type="text" id="addPseudo" class="form-control" placeholder="pseudo">
        <button type="submit" id="addUser" class="btn btn-outline-primary">Ajouter</button>
    </div>
</div>

<br>

<div class="container">
    <h1>Désinscription</h1>
    <div class="form-inline">
        <button type="submit" id="desinscription" class="btn btn-outline-danger">Désinscription</button>
    </div>
</div>

<br>

<div class="container">
    <h1>Liste d'amis</h1>
</div>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Pseudo</th>
            <th>Prenom</th>
            <th>Nom</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="tableauAmi">
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>

<script>

    $(function () {
        update();

        function update() {
            $.post('profil',
                {"action": "update"},
                function (data, status) {
                    res = JSON.parse(data);
                    $('#tableauAmi').html('');
                    for (var i = 0; i < res.length; i++) {
                        var tmp = '<tr>';
                        tmp += '<td class="text-center">' + res[i].id + '</td>';
                        tmp += '<td class="text-center">' + res[i].pseudo + '</td>';
                        tmp += '<td class="text-center">' + res[i].prenom + '</td>';
                        tmp += '<td class="text-center">' + res[i].nom + '</td>';
                        if (res[i].status === 1) {
                            tmp += '<td class="text-center">' +
                                '<button type="button" value=' + res[i].id + ' class="btn btn-outline-danger">Supprimer</button> </td>';
                        } else if (res[i].status === 0) {
                            tmp += '<td class="text-center"> En attente d\'acceptation ...</td>';
                        } else if (res[i].status === 2) {

                            tmp += '<td class="text-center">' +
                                '<button type="button" id="accept" value=' + res[i].id + ' class="btn btn-outline-success">Accepter</button>';
                            tmp += '<button type="button" id="refuse" value=' + res[i].id + ' class="btn btn-outline-danger">Refuser</button> </td>';
                        }
                        tmp += '</tr>';
                        $('#tableauAmi').append(tmp);
                    }
                });
        }

        $(document).on('click', 'button', function () {
            if (this.id === "addUser") {
                $.post('profil', {"action": "add", "pseudo": document.getElementById("addPseudo").value},
                    function (data, status) {
                        update();
                    });
            } else if (this.id === "accept") {
                $.post('profil',
                    {"action": "accept", "id": $(this).val()},
                    function (data, status) {
                        update();
                    });
            } else if (this.id === "refuse") {
                var res = confirm('Êtes-vous sûr de vouloir refuser cette ami ?');
                if (res === true) {
                    $.post('profil',
                        {"action": "suppr", "id": $(this).val()},
                        function (data, status) {
                            update();
                        });
                }
            } else if (this.id === "desinscription") {
                var res = confirm('Êtes-vous sûr de vouloir vous désinscrire ?');
                if (res === true) {
                    $.post('profil',
                        {"action": "desins", "id": $(this).val()},
                        function (data, status) {
                            update(); //todo desins redirect
                        });
                }
            } else {
                var res = confirm('Êtes-vous sûr de vouloir supprimer cette ami ?');
                if (res === true) {
                    $.post('profil',
                        {"action": "suppr", "id": $(this).val()},
                        function (data, status) {
                            update();
                        });
                }
            }
        });

        //setInterval(update, 4000);
    });

</script>

</body>
</html>
