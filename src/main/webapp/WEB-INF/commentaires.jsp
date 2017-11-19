<%--
  Created by IntelliJ IDEA.
  User: evergreen
  Date: 04/11/2017
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%--@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" --%>
<%--@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>

<div class="mainComment">

    <section class="nouveauCommentaire">

       <!-- <form class="bar-tweet" method="post" action="critiques" > -->
        <form class="bar-tweet" method="post" action="javascript:commentaire(this);return false;" >

            <div class="form-group">
                <label for="comment">Critique :</label>
                <textarea class="form-control" id="comment" placeholder="MESSAGE(300 caractères maximum) ici..."
                          name="commentaire" rows="5"
                          maxlength="300"></textarea>
                <button type="submit" id="addComment" value="envoyer" name="message" class="but">Envoyer</button>
                <a href="accueil.html"><input class="but" type="reset" value="Annuler" name="Annuler"/></a>
            </div>
        </form>
    </section>

    <section id ="Allcritique" class="Allcommentaire">
        <h1>COMMENTAIRES</h1>
        <div id="comments" class="fond">
            ICI
           <%--  <c:forEach var="item" items="${message}" >
             <c:out value="${item.message}" /> <br/>
            </c:forEach> --%>

        </div>
   </section>
    <script>

        /* PARTIE COMMENTAIRE */




        /* function gestionCommentaire(comment) {

             //$('#comments').html('');
             var temp = '<div id="video" class="col-sm-8">';
             temp += '<video id="my-video" class="video-js" controls preload="auto" width="540" height="264" poster="../../images/ronaldo.jpg" data-setup="{}">';
             temp += '<source src="../../animations/'+urlVideo+'" type="video/mp4"/>';
             // temp += '<source src="../../animations/'+urlVideo+'" type="video/webm"/>';
             temp += '<p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a web browser that';
             temp +='<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>';
             temp += '</p>';
             temp += '</video>';
             temp += '</div>';
             $('#bb').append(temp);

         }*/

        $(function () {
            $(document).ready(function() {
                getCommentaires();
            });

            function sendCommentaire(comment) {
                $.post('commentaires',
                    {"action": "addComment", "comment": comment},
                    function (data, status) {
                        $('#comments').html('');
                        var tmp = "";
                        tmp += "<div id=\"commentaire\">  " + data + "</div>";
                        $('#comments').append(tmp);
                    });
            }

            function getCommentaires() {
                $.post('commentaires',
                    {"action": "getComment"},
                    function (data, status) {
                        $('#comments').html('');
                        var tmp = "";
                        tmp += "<div id=\"commentaire\">  " + data + "</div>";
                        $('#comments').append(tmp);
                    });
            }


            var addComment = document.getElementById('addComment');
            addComment.addEventListener('click', function() {

                var comment = $("textarea").val();
                sendCommentaire(comment);

            }, false);

            setInterval(getCommentaires, 5000);
        });


    </script>
</div>



