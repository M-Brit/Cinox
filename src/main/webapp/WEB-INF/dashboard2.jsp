<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="icon" href="../../../../favicon.ico">-->

    <title>Cinox</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="../css/jumbotron.css" rel="stylesheet">
</head>
<body>
<!-- Header de la page -->
<%@ include file="header.jsp" %>

<!--
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="first-slide"
                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="First slide">
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Example headline.</h1>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                            gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="second-slide"
                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Another example headline.</h1>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                            gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="third-slide"
                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="Third slide">
                <div class="container">
                    <div class="carousel-caption text-right">
                        <h1>One more for good measure.</h1>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                            gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
-->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container">

    <div class="row">
        <div class="nomAlbum"><h1 id="categorie"></h1></div>
        <div id="films" >

        </div>
    </div>

</div><!-- /.container -->

<!-- footer de la page -->
<%--@ include file="footer.jsp" --%>

<script src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>

    var notetmp = 1;

    $(function () {
        $(document).ready(function () {
            //get all films
            //getFilm();

            //get now playing films
            getFilmByType('nowPlaying');

        });

        $('input').on('click', function () {
            var test = $(this).val;
            console.log('input==' + test)
        });

        function getFilm() {
            $.get('films',
                function (data, status) {
                    var imageUrl = 'https://image.tmdb.org/t/p/w500';
                    //console.log("data : "+data.length );
                    res = JSON.parse(data);
                    $('#films').html('');
                    res.forEach(function (element) {
                        var tmp = "";
                        var imgtest  = element.poster_path;
                       if(imgtest !== null && imgtest !== "" ) {
                            tmp += '<div class="col-sm-3 eachMovie">';
                            tmp += '<div id="film" class="col-sm-7 card eachAlbum">';
                            tmp += '<div class="btnModal"> <a href="#" onclick="filmDetails(' + element.id + ')"> <img class="imgModal" src="' + imageUrl + "" + element.poster_path + '" title="IMAGES" alt=\"Card image cap\"/></a></div>';
                            //tmp += '<p class="card-text"> Titre : '+ element.title +"<br/> Date de sortie : "+ element.release_date;
                            //TODO : tmp += '<p class="card-text"> '+element.overView+'</p>';
                            // tmp += '<br/> Notes : '+element.vote_average+'</p>';
                            tmp += "</div>";
                            $('#films').append(tmp);
                            $('#categorie').html("Films");
                       }
                    });

                });
        }

        function getSearchFilm(objsearch) {
            alert('submitsearch1');
            $.post('search',
                {"titleFilm": objsearch},
                function (data, status) {
                    var imageUrl = 'https://image.tmdb.org/t/p/w500';
                    res = JSON.parse(data);
                    //alert("searchRes : " + data);
                    $('#films').html('');
                    res.forEach(function (element) {
                        var tmp = "";
                        var imgtest  = element.poster_path;
                        if(imgtest !== null && imgtest !== "" ) {
                            tmp += '<div class="col-sm-3 eachMovie">';
                            tmp += '<div id="film" class="col-sm-7 card eachAlbum">';
                            tmp += '<div class="btnModal"> <a href="#" onclick="filmDetails(' + element.id + ')"> <img class="imgModal" src="' + imageUrl + "" + element.poster_path + '" title="IMAGES" alt=\"Card image cap\"/></a></div>';
                            tmp += "</div>";
                            $('#films').append(tmp);
                            $('#categorie').html("Search"); // TODO mettre ici le nom de la categorie ;)
                        }
                    });
                });
        }

        var submitsearch = document.getElementById('submitsearch');
        submitsearch.addEventListener('click', function () {
            var objsearch = document.getElementById('search').value;
            alert('submitsearch44 : '+objsearch);
            getSearchFilm(objsearch);

        }, false);


        //  setInterval(getCommentaires, 5000);
    });

    function filmDetails(id) {
        //alert('id==' + id);
        notetmp = id;
       $.post(
            'filmDetails',
            {"id": id},
            function (data, status) {
                var imageUrl = 'https://image.tmdb.org/t/p/w500';
                var videoUrl = 'https://www.youtube.com/watch?v=';
                res = JSON.parse(data);
                $('#categorie').html('');
                $('#films').html('');
                var video = res.video;

                var tmp = "";
                tmp += '<div class="detailFilm">';
                tmp += '<section class="imgFilm"><img class="imgAffiche" src="' + imageUrl + "" + res.poster_path + '"/>';
                tmp += '<aside class="dateNote"><p> Date de sortie : ' + res.release_date + '<br/>';
                tmp += ' Note MovieDB : ' + res.vote_average + '/10';
                tmp += '<span class="rating" id="rating">' +
                            '<input name="stars" value="5" id="e5" type="radio"/><label for="e5">\u2605</label>' +
                            '<input name="stars" value="4" id="e4" type="radio"/><label for="e4">\u2605</label>' +
                            '<input name="stars" value="3" id="e3" type="radio"/><label for="e3">\u2605</label>' +
                            '<input name="stars" value="2" id="e2" type="radio"/><label for="e2">\u2605</label>' +
                            '<input name="stars" value="1" id="e1" type="radio"/><label for="e1">\u2605</label>' +
                        '</span>';
                tmp += '<span class="container" id="note"></span></p></aside></section>';
                tmp += '<section class=" sectionFilm"><aside class =" descriptionFilm">';
                tmp += '<h3> Synopsis : </h3>';
                tmp += '<p>' + res. overview + '</p></aside>';
                tmp += '<aside><div id="ytplayer"></div></aside></section>';

                // SPLIT POUR LES ACTEURS
                var acteurs = res.acteurs;
                tmp += '<section class="acteursFilm"><table><tr>';
                acteurs.forEach(function (element) {
                    var arrayacteurImages = element.split("/");

                    for (var i=0; i < arrayacteurImages.length ; i=i+2) {
                        var nameActeur = arrayacteurImages[i];
                        var imageActeur = arrayacteurImages[i+1];

                        tmp += '<td><img class="imgActeur" src="' + imageUrl +"/"+ imageActeur + '"/>';
                        tmp += '<p>'+nameActeur+'</p></td>'

                    }

                });
                tmp += "</tr></table></section>";
                // POUR LES COMMENTAIRES
                tmp += '<div class="mainComment">';
                tmp += '<section class="nouveauCommentaire" >';
                tmp += '<div class="form-group">';
                tmp += '<label for="comment">COMMENTAIRE :</label>';
                tmp += '<textarea class="form-control" id="commentaire" placeholder="ici..." name="comment" rows="5" maxlength="300"></textarea>';
                tmp += '<a href="#" onclick="addCommentaire(res.id)" id="test"> <button type="submit" id="submitCommentaire" value="envoyer" name="ajouteCommentaire" class="but">Envoyer</button></a>';
                tmp += '</div>';
                tmp += '</div>';

                $.post('commentaires',
                    {"action": "getComment", "comment": null, "idFilm":res.id,}
                ).done(function (res, status) {
                    alert('status test=='+ status);
                    commentaire = JSON.parse(res);
                    commentaire.forEach(function (mm) {
                        tmp += '<div class="media-grids">';
                        tmp += '<div class="media">';
                        tmp += '<h5 class="name_user">'+mm.idUser+'</h5> <a href="../images/fb.png"></a>';
                        tmp += '<div class="media-body">';
                        tmp += '<p>'+mm.critique+'</p>';
                        tmp += '</div>';
                        tmp += '</div>';
                    })
                });

                alert('tmp=='+ tmp)

                 $('#categorie').html(res.title);
                 $('#films').append(tmp);

                 onYouTubePlayerAPIReady();

                 // Load the IFrame Player API code asynchronously.
                 var tag = document.createElement('script');
                 tag.src = "https://www.youtube.com/player_api";
                 var firstScriptTag = document.getElementsByTagName('script')[0];
                 firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

                 // Replace the 'ytplayer' element with an <iframe> and
                 // YouTube player after the API code downloads.
                 var player;

                 function onYouTubePlayerAPIReady() {
                     player = new YT.Player('ytplayer', {
                         height: '360',
                         width: '640',
                         videoId: video
                     });
                 }




                 update();
                 updateMoyenne();
            });
    }

    function addCommentaire(id) {
        alert('yes yes yes'+ id);
        var commentaire = document.getElementById('commentaire').value;
        $.post('commentaires',
            {"action": "addComment", "comment": commentaire, "idFilm":id}
        ).done(function (data, status) {
                filmDetails(id);
            });
    }

    // Load the IFrame Player API code asynchronously.
    var tag = document.createElement('script');
    tag.src = "https://www.youtube.com/player_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // Replace the 'ytplayer' element with an <iframe> and
    // YouTube player after the API code downloads.
    var player;

    function onYouTubePlayerAPIReady() {
        player = new YT.Player('ytplayer', {
            height: '360',
            width: '640',
            videoId: videoUrl + video
        });
    }


    function getFilmByCategory(categoryId, cat) {
        $.post('filmCategory',
            {"categoryId": categoryId},
            function (data, status) {
                var imageUrl = 'https://image.tmdb.org/t/p/w500';
                res = JSON.parse(data);
                $('#films').html('');
                res.forEach(function (element) {
                    var tmp = "";
                    var imgtest  = element.poster_path;
                    if(imgtest !== null && imgtest !== "" ) {
                        tmp += '<div class="col-sm-3 eachMovie">';
                        tmp += '<div id="film" class="col-sm-7 card eachAlbum">';
                        tmp += '<div class="btnModal"> <a href="#" onclick="filmDetails(' + element.id + ')"> <img class="imgModal" src="' + imageUrl + "" + element.poster_path + '" title="IMAGES" alt=\"Card image cap\"/></a></div>';
                        //tmp += '<p class="card-text"> Titre : '+ element.title +"<br/> Date de sortie : "+ element.release_date;
                        //TODO ajouter css: tmp += '<p class="card-text"> '+element.overview+'</p>';
                        // tmp += '<br/> Notes : '+element.vote_average+'</p>';
                        tmp += "</div>";
                        $('#films').append(tmp);
                        $('#categorie').html(cat); // TODO mettre ici le nom de la categorie ;)
                    }
                });
            });
    }

    function getFilmByType(type) {
        $.post('filmType',
            {"filmType": type},
            function (data, status) {
                var imageUrl = 'https://image.tmdb.org/t/p/w500';
                res = JSON.parse(data);
                $('#films').html('');
                res.forEach(function (element) {
                    var tmp = "";
                    var imgtest  = element.poster_path;
                    if(imgtest !== null && imgtest !== "" ) {
                        tmp += '<div class="col-sm-3 eachMovie">';
                        tmp += '<div id="film" class="col-sm-7 card eachAlbum">';
                        tmp += '<div class="btnModal"> <a href="#" onclick="filmDetails(' + element.id + ')"> <img class="imgModal" src="' + imageUrl + "" + element.poster_path + '" title="IMAGES" alt=\"Card image cap\"/></a></div>';
                        //tmp += '<p class="card-text"> Titre : '+ element.title +"<br/> Date de sortie : "+ element.release_date;
                        //TODO : tmp += '<p class="card-text"> '+element.overView+'</p>';
                        // tmp += '<br/> Notes : '+element.vote_average+'</p>';
                        tmp += "</div>";
                        $('#films').append(tmp);
                        $('#categorie').html(type); //TODO mettre ici le nom de la categorie ;)
                    }
                });
            });
    }


    $(document).on('change', 'input:radio', function () {
        if (this.id === "e1") {
            sendNote(e1.value);
        } else if (this.id === "e2") {
            sendNote(e2.value);
        } else if (this.id === "e3") {
            sendNote(e3.value);
        } else if (this.id === "e4") {
            sendNote(e4.value);
        } else if (this.id === "e5") {
            sendNote(e5.value);
        }

    });

    function update() {
        $.post("notation", {"action": "getNote", "idfilm": notetmp},
            function (data, status) {
                for (var i = 1; i < 6; i++) {
                    if (i === parseInt(data)) {
                        var tmp = "e" + i;
                        document.getElementById(tmp).checked = true;
                    } else {
                        var tmp = "e" + i;
                        document.getElementById(tmp).checked = false;
                    }
                }
            }
        );
    }

    function updateMoyenne() {
        $.post("notation", {"action": "getMoyenne", "idfilm": notetmp},
            function (data, status) {
                if (parseInt(data) === -1) {
                    document.getElementById("note").innerHTML = "Pas de note.";
                } else {
                    document.getElementById("note").innerHTML = "Note Cinox : "+parseFloat(data)+"/5";
                }
            });
    }

    function sendNote(value) {
        $.post("notation", {"action": "addNote", "idfilm":notetmp, "note":value},
            function (data, status) {
                update();
                updateMoyenne();
            });
    }
</script>
</body>
</html>
