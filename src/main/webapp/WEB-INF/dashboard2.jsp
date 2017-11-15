<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="icon" href="../../../../favicon.ico">-->

    <title>Carousel Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/jumbotron.css" rel="stylesheet">
</head>
<body>
<!-- Header de la page -->
<%@ include file="header.jsp" %>

<main role="main">

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


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">



        <div class="album text-muted">
            <div id="films" class="container">
            </div>
        </div>



    </div><!-- /.container -->
    <div>
        <section class>

        </section>
    </div>
</main>
<!-- footer de la page -->
<%@ include file="footer.jsp" %>



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
        $(document).ready(function() {
            getFilm();
        });

        $('input').on('click', function(){
            var test = $(this).val;
            console.log('input=='+ test)
        });

        function getFilm() {
            $.get('films',
                function (data, status) {
                var imageUrl ='https://image.tmdb.org/t/p/w500';
                  //console.log("data : "+data.length );
                    alert("TEST"+data);
                   res = JSON.parse(data);
                   $('#films').html('');
                    res.forEach(function(element) {
                    var tmp = "";
                    tmp += "<div id=\"film\">  " + element.title;
                    tmp += '<p>'+element.release_date+'</p>';
                        tmp += '<p>'+element.overView+'</p>';
                        tmp += '<p>'+element.vote_average+'</p>';
                    tmp += '<a href="#" onclick="filmDetails('+ element.id+')"> <img src="'+ imageUrl+""+element.poster_path+'"/></a>';
                    tmp += "</div>";
                    $('#films').append(tmp);

                  });

                });
        }

        function getSearchFilm(objsearch) {
            console.log('submitsearch2')
            $.post('search',
                {"titleFilm": objsearch},
                function (data, status) {
                    var imageUrl ='https://image.tmdb.org/t/p/w500';

                    res = JSON.parse(data);
                    alert("searchRes : "+data);
                    $('#films').html('');
                    res.forEach(function(element) {
                        var tmp = "";
                        tmp += "<div> " + element.title;
                        tmp += '<p>'+element.release_date+'</p>';
                        tmp += '<p>'+element.vote_average+'</p>';
                        tmp += '<img src="'+ imageUrl+""+element.poster_path+'"/>';
                        tmp += "</div>";
                        $('#films').append(tmp)

                    });
                    alert("TMP : "+tmp);
                });
        }

        var submitsearch = document.getElementById('submitsearch');
        console.log('submitsearch')
        submitsearch.addEventListener('click', function() {
            var objsearch= document.getElementById('search').value;
            getSearchFilm(objsearch);

        }, false);




      //  setInterval(getCommentaires, 5000);
    });

    function filmDetails(id) {
        alert('id=='+ id)
        $.post(
            'filmDetails',
            {"id": id},
            function (data, status) {
                var imageUrl ='https://image.tmdb.org/t/p/w500';
                var videoUrl = 'https://www.youtube.com/watch?v=';
                res = JSON.parse(data);
                alert("filmDetails : "+data);
                $('#films').html('');
                //res.forEach(function(element) {
                element = res[0];
                video = ((element.videos).results[0]).key;
                alert('videosss=='+ video)
                    var tmp = "";
                    tmp += "<div> " + element.title;
                    tmp += '<p>'+element.release_date+'</p>';
                alert('3'+ element.vote_average)
                    tmp += '<p>'+element.vote_average+'</p>';
                alert('4'+ imageUrl+ element.poster_path)
                    tmp += '<img src="'+ imageUrl+""+element.poster_path+'"/>';
                alert('5'+ videoUrl+ video)
                    tmp+= '<div id="ytplayer"></div>';
                    tmp += "</div>";
                    $('#films').append(tmp)

                //});
                alert("TMP : "+tmp);
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
        console.log('ssss=='+ video)
        player = new YT.Player('ytplayer', {
            height: '360',
            width: '640',
            videoId: videoUrl + video
        });
    }

</script>
</body>
</html>
