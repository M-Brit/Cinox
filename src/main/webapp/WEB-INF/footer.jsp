<%--
  Created by IntelliJ IDEA.
  User: evergreen
  Date: 03/11/2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<!-- FOOTER -->
<footer class="container">
    <div id="foot">
    <ul class="bs-docs-footer-links">
        <li> <a  href="https://github.com/M-Brit/Cinox.git" role="button"> GitHub </a> </li>
        <li> <a  href="https://facebook.com/" role="button"> Facebook </a> </li>
        <li> <a  href="https://twitter.com/" role="button"> Twitter </a> </li>
        <li> <a  href="#" role="button"> About </a> </li>
    </ul>
    </div>
</footer>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>









<header>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <a class="navbar-brand" href="#">Cinox</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Populaire<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Prochainement</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">A l'affiche</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Profil</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input id="search" class="form-control mr-sm-2" name="submitsearch" type="search" placeholder="Search" aria-label="Search">
                <button id ="submitsearch" class="btn btn-outline-success my-2 my-sm-0" type="submit">Recherche</button>
            </form>
            <button class="btn btn-outline-danger " href="deconnexion" type="submit">Deconnexion</button>
        </div>
    </nav>


</header>