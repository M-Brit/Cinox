<%--
  Created by IntelliJ IDEA.
  User: evergreen
  Date: 03/11/2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="test.css">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
            <input id = "search" name="submitsearch" vclass="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button id ="submitsearch" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

            <!-- </form> -->
            <button class="btn btn-outline-danger my-2 my-sm-0" href="deconnexion" type="submit">Deconnexion</button>
        </div>
    </nav>
</header>
