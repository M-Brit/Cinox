<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="api.MovieApi"%>
<%@page import="beans.Movie"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Movies</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="My Play Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

    <!-- bootstrap -->
    <link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all" />
    <!-- //bootstrap -->
    <link href="css/dashboard.css" rel="stylesheet">
    <!-- Custom Theme files -->
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all" />

    <!--start-smoth-scrolling-->
    <!-- fonts -->
    <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <!-- //fonts -->
</head>
<body>

<!-- Header de la page -->
<%@ include file="header.jsp" %>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="top-navigation">
        <div class="t-menu">MENU</div>
        <div class="t-img">
            <img src="images/lines.png" alt="" />
        </div>
        <div class="clearfix"> </div>
    </div>

</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="show-top-grids">
        <div class="col-sm-10 show-grid-left main-grids">
            <div class="recommended">
                <div class="recommended-grids english-grid">
                    <div class="recommended-info">
                        <div class="heading">
                            <h3>Now Playing</h3>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <%
                        MovieApi movieApi = new MovieApi();
                        Movie playingMovie;
                        String playing_image;
                        ArrayList<Movie> playingMovies = movieApi.nowPlaying();
                        for (int i=0; i<playingMovies.size(); i++) {
                            playingMovie = playingMovies.get(i);
                            playing_image = "https://image.tmdb.org/t/p/w500"+playingMovie.getPoster_path();
                    %>
                    <div class="col-md-3 resent-grid recommended-grid movie-video-grid">
                        <div class="resent-grid-img recommended-grid-img">
                            <a href="movieDetails.jsp?id=<%=playingMovie.getId()%>"><img src=<%=playing_image%> alt="" /></a>
                        </div>
                        <div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
                            <h5><a href="single.html" class="title"><%=playingMovie.getTitle()%></a></h5>
                            <ul>
                                <li class="right-list"><p class="views views-info">vote_average:<%=playingMovie.getVote_average()%></p></li>
                            </ul>
                        </div>
                    </div>
                    <%}%>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="recommended">
                <div class="recommended-grids">
                    <div class="recommended-info">
                        <div class="heading">
                            <h3>UpComing</h3>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <%
                        Movie upComingMovie;
                        String upComing_image;
                        ArrayList<Movie> upComingMovies = movieApi.upComing();
                        for (int j=0; j<upComingMovies.size(); j++) {
                            upComingMovie = upComingMovies.get(j);
                            upComing_image = "https://image.tmdb.org/t/p/w500"+upComingMovie.getPoster_path();
                    %>
                    <div class="col-md-3 resent-grid recommended-grid movie-video-grid">
                        <div class="resent-grid-img recommended-grid-img">
                            <a href="movieDetails.jsp?id=<%=upComingMovie.getId()%>"><img src=<%=upComing_image%> alt="" /></a>
                            <div class="clck movie-clock">
                                <span class="glyphicon glyphicon-time" aria-hidden="true"></span>
                            </div>
                        </div>
                        <div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
                            <h5><a href="single.html" class="title"><%=upComingMovie.getTitle()%></a></h5>
                            <ul>
                                <li class="right-list"><p class="views views-info">vote_average:<%=upComingMovie.getVote_average()%></p></li>
                            </ul>
                        </div>
                    </div>
                    <%}%>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="recommended">
                <div class="recommended-grids">
                    <div class="recommended-info">
                        <div class="heading">
                            <h3>Top Rated</h3>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <%
                        Movie topMovie;
                        String topMovie_image;
                        ArrayList<Movie> topMovies = movieApi.topRated();
                        for (int k=0; k<topMovies.size(); k++) {
                            topMovie = topMovies.get(k);
                            topMovie_image = "https://image.tmdb.org/t/p/w500"+topMovie.getPoster_path();
                    %>
                    <div class="col-md-3 resent-grid recommended-grid movie-video-grid">
                        <div class="resent-grid-img recommended-grid-img">
                            <a href="movieDetails.jsp?id=<%=topMovie.getId()%>"><img src=<%=topMovie_image%> alt="" /></a>
                            <div class="clck movie-clock">
                                <span class="glyphicon glyphicon-time" aria-hidden="true"></span>
                            </div>
                        </div>
                        <div class="resent-grid-info recommended-grid-info recommended-grid-movie-info">
                            <h5><a href="single.html" class="title"><%=topMovie.getTitle()%></a></h5>
                            <ul>
                                <li class="right-list"><p class="views views-info">vote_average:<%=topMovie.getVote_average()%></p></li>
                            </ul>
                        </div>
                    </div>
                    <%}%>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>

        <div class="clearfix"> </div>
    </div>
    <!-- footer -->
    <div class="footer">

    </div>
    <!-- //footer -->
</div>
<div class="clearfix"> </div>
<div class="drop-menu">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
        <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
    </ul>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
</body>
</html>
