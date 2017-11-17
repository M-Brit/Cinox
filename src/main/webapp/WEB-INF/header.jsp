<%--
  Created by IntelliJ IDEA.
  User: evergreen
  Date: 03/11/2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--<header>
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
            <!-- <form class="form-inline mt-2 mt-md-0"  method="post" action="dashboard2.jsp"> -->
  <!--          <input id = "search" name="submitsearch" vclass="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button id ="submitsearch" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
-->
            <!-- </form> -->
  <!--          <button class="btn btn-outline-danger my-2 my-sm-0" href="deconnexion" type="submit">Deconnexion</button>
        </div>
    </nav>
-->

    <!--

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Project name</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                    </form>
                </div>
            </div>
        </nav --> <!--/.navbar-collapse -->


<!--  -->
<!-- Navbar -->
<!--  -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Cinox</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="nav-item ">
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
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Genres<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" class="action" id="action">Action</a></li>
                        <li><a href="#" class="adventure" id="adventure">Adventure</a></li>
                        <li><a href="#" class="animation" id="animation">Animation</a></li>
                        <li><a href="#" class="comedy" id="comedy">Comedy</a></li>
                        <li><a href="#" class="crime" id="crime">Crime</a></li>
                        <li><a href="#" class="drama" id="drama">Drama</a></li>
                        <li><a href="#" class="family" id="family">Family</a></li>
                        <li><a href="#" class="fantasy" id="fantasy">Fantasy</a></li>
                        <li><a href="#" class="history" id="history">History</a></li>
                        <li><a href="#" class="music" id="music">Music</a></li>
                        <li><a href="#" class="romance" id="romance">Romance</a></li>
                        <li><a href="#" class="scifi" id="scifi">Science Fiction</a></li>
                        <li><a href="#" class="thriller" id="thriller">Thriller</a></li>
                    </ul>
                </li>
                <li class="gitHubLogo">
                    <a href="https://github.com/dangconnie/movie-app" target="_blank">GitHub Repo</a>
                </li>
                <!-- <li><a href="#">Specials</a></li> -->
                <!--      <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Extras <span class="caret"></span></a>
                       <ul class="dropdown-menu">
                         <li><a href="#">VIP Rewards Card</a></li>
                         <li><a href="#">Specials and Promotions</a></li>
                         <li><a href="#">Gift Cards</a></li>
                       </ul>
                     </li> -->
                <!-- <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Locations<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Atlanta</a></li>
                    <li><a href="#">Buckhead</a></li>
                    <li><a href="#">Midtown</a></li>
                    <li><a href="#">Marietta</a></li>
                  </ul>
                </li> -->
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form class="navbar-form navbar-right searchForm">
                    <div class="form-group">
                       <label for="submitsearch"><input id = "search" type="text" class="form-control" placeholder="Search movies"></label>
                    </div>
                    <button type="submit" id ="submitsearch" class="btn btn-success">Envoyer</button>
                    <button type="submit" href="deconnexion" class="btn btn-danger">Deconnexion</button>
                </form>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


















</header>
