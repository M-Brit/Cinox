function critiqueFilm(){

   // var key=location.search.substring(5, 37);
    recherchecommentamis(key);
}

function recherchecommentamis(key){
    $.ajax({type :"GET",
            url:"critiques",
            data:"key="+key,
            dataType:"text",
            success:traiteReponseAfficheCommentAmis,
            error:function (jqXHR, textestatus, errorThrows){
                alert(jqXHR+" "+textestatus+" "+errorThrows);
            }
        }
    );
}

function traiteReponseAfficheCommentAmis(rep){
    $(document).ready(function(){
        i=0;
        if (rep.NBComment != 0)
            $('#donnees').append("<p>message<p>");

        for(j=0;j<rep.NBComment;j++){

            login=rep['commentaire'+j].login;
            message=rep['commentaire'+j].message;

            $('#donnees').append("<div class=\"commentaire\"> Message de "+login+"<p>"+message+"<p>"+
                "<form name=\"formu1\">"+
                "<input type=\"button\" value=\"Supp\" onClick=\"removeFriends(login)\""+
                "</form></div>");
        }
    });
}



////////////////FONCTION ADDCOMMENT

function commentaire(formulaire){

    var key = location.search.substring(5, 37);
    var message = formulaire.comment.value;

    $.ajax({    type: "POST",
                url: "critiques",
                data: {"action" : "addComment", "critiques" : "message" },
                dataType: "json",
                success:traiteReponseCommentaire,
                error: function (jqXHR, textestatus, errorThrows){
                            alert(jqXHR+" "+textestatus+" "+errorThrows);
                       }
    });
}


function traiteReponseCommentaire(){
    // TODO :  mettre code qui genere .
}


/*
function addcomments(key, message){

    $.ajax({type :"GET",
        url:"AddComment",
        data:"key="+key+"&message="+message,
        dataType:"json",
        success:traiteReponseCommentaire,
        error:function (jqXHR, textestatus, errorThrows){
            alert(jqXHR+" "+textestatus+" "+errorThrows);
        }
    });
}

function traiteReponseCommentaire(){
    if(rep.erreur==undefined){
        window.location.href="main.jsp";
    }
    else{
        alert("erreur= "+rep.erreur);
    }
}


function refresh() {
    $.ajax({
        url: "critique",
        success:
            function(retour){
                $('#Allcritique').html(retour);
            }
    });

}
*/



function traiteReponseCommentaire(critiquesArray){
   // $('#donnees').html('');
    $(document).ready(function(){
        //$('#').remove();
         critiquesArray.forEach(
             function(jsonCritique) {
                 $('#donnees').append(

                     "<div id=\"commentaire\">  " + jsonCritique.critique + "</div>"
                     );
         });
    });
}

function recupCommentaire() {
    $.ajax({
        type: "GET",
        url: "commentaires",
        dataType: "json",
        success: traiteReponseCommentaire,
        error:
            function () {
                alert("ERROR");
            }
    });
}
function main(){
    recupCommentaire();
}