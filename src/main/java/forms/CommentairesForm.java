package forms;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import exceptions.CommentairesException;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.HibernateUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CommentairesForm {
    private static final String CHAMP_CRITIQUES = "critiques";
    // TODO :  Mettre les id recuperer de la page du film.
    private static final String CHAMP_ERROR = "errorInsertFilm";
    private static final String CHAMP_USER = "idUser";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public JSONArray commentaireFilm(String idFilm, String idUser, String userName, String commentaire){
        return addCritiquesFilm(idFilm, idUser,userName, commentaire);
    }

    public JSONArray addCritiquesFilm(String idFilm, String idUser, String userName, String commentaires){
        MongoCollection<Document> collection = this.connexionMongoDB();
        //insert
        Document document = new Document()
                .append("idUser", idUser)
                .append("userName", userName)
                .append("idFilm", Integer.valueOf(idFilm))
                .append("critique", commentaires)
                .append("date", new Date().toString());
        collection.insertOne(document);

        //select and reture
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> mongoCursor = documents.iterator();

        org.json.JSONArray array = new org.json.JSONArray();
        org.json.JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new org.json.JSONObject(doc.toJson());
            array.put(object);
        }
        return  array;
    }

    public JSONArray obtainCommentaires(String idFilm){
        return getCritiques(idFilm);
    }

    private JSONArray getCritiques(String idFilm){

        MongoCollection<Document> collection = this.connexionMongoDB();
        BasicDBObject query = new BasicDBObject();
        query.put("idFilm", Integer.valueOf(idFilm));
        FindIterable<Document> documents = collection.find(query);

        MongoCursor<Document> mongoCursor = documents.iterator();

        org.json.JSONArray array = new org.json.JSONArray();
        org.json.JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new org.json.JSONObject(doc.toJson());
            array.put(object);
        }
        return  array;
    }
    //-----------------------------------------------------


    /**
     * Permet de se connecter à la base de donnée.
     * @return MongoCollection<Document> qui permet ensuite de se connecter a la base de donnée et
     */
/*    private MongoCollection<Document> connexionMongoDB(){


        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase database = mongo.getDatabase("cinoxNoSqlDB");
        // Accessing the database
        MongoCollection<Document> collection = database.getCollection("commentaire");

        return collection;
    }*/
    private MongoCollection<Document> connexionMongoDB(){

        // Accessing the database
        MongoCollection<Document> collection = HibernateUtil.database.getCollection("commentaire");

        return collection;
    }



    public void testCommentaire(JSONObject comment) throws CommentairesException {
        System.out.println("TEST COM1");
        // todo -> changer critique en commentaire + ajout affichage utilisateur
        if(comment.get("critique").toString().equals("") || comment.get("critique").toString() == null){
            throw new CommentairesException("commentaire vide");
        }
        if(comment.get("idUser").toString().equals("") || comment.get("idUser").toString() == null){
            throw new CommentairesException("idUser inconnu");
        }
        if(comment.get("idFilm").toString().equals("") || comment.get("idFilm").toString() == null){
            throw new CommentairesException("idFilm inconnu");
        }
        System.out.println("TEST COM2");
    }




    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     *
     * @param champ
     * @param message
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }


    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon. -> permet d'éviter les espaces en trop et les valeur null ou vide.
     *
     * @param request
     * @param nomChamp
     * @return
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }


    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
}