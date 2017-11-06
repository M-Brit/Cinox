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
                '',
                function (data, status) {
                    res = JSON.parse(data);
                    $('#tableauAmi').html('');
                    for (var i = 0; i < res.length; i++) {
                        var tmp = '<tr>';
                        tmp += '<td class="text-center">' + res[i].id + '</td>';
                        tmp += '<td class="text-center">' + res[i].pseudo + '</td>';
                        tmp += '<td class="text-center">' + res[i].prenom + '</td>';
                        tmp += '<td class="text-center">' + res[i].nom + '</td>';
                        tmp += '<td class="text-center">' +
                            '<button type="button" value=' + res[i].id + ' class="btn btn-danger">Supprimer</button>';
                        tmp += '</tr>';
                        console.log(tmp);
                        $('#tableauAmi').append(tmp);
                    }
                });
        }

        $(document).on('click', 'button', function () {
            confirm('Êtes-vous sûr de vouloir supprimer cette ami ?');
            $.post('profil',
                'suppr',
                function (data, status) {
                    update();
                });
        });

        //setInterval(update, 4000);
    });

</script>

</body>
</html>
