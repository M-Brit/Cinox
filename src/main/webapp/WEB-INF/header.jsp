<%--
  Created by IntelliJ IDEA.
  User: evergreen
  Date: 03/11/2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">Cinox</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Films <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Populaire <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Mieux notés</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Prochainement</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Actuellement en Salles</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Profil
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>

            </ul>
            <!-- <form class="form-inline mt-2 mt-md-0"  method="post" action="dashboard2.jsp"> -->
                <input id = "search" name="submitsearch" vclass="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button id ="submitsearch" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

            <!-- </form> -->
            <a class="btn btn btn-outline-danger" href="deconnexion"  type="warning" role="button">Sign out</a>
        </div>
    </nav>
</header>