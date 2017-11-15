package forms;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import exceptions.CommentairesException;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public final class CommentairesForm {
    private static final String CHAMP_CRITIQUES = "critiques";
    // TODO :  Mettre les id recuperer de la page du film.
    private static final String CHAMP_ERROR = "errorInsertFilm";
    private static final String CHAMP_USER = "idUser";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public JSONArray getArrayComment() {
        return arrayComment;
    }

    private JSONArray arrayComment = new JSONArray();
    public void commentaireFilm(HttpServletRequest request) throws ParseException {

        String commentaire = request.getParameter("comment").toString();


        // TODO recuperer idfilm et idUser
        this.addCritiquesFilm("10", "100", commentaire);

        // Initialisation du résultat global de la validation.
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

    }

    public JSONArray obtainCommentaires(HttpServletRequest request){
        try {
            getCritiques(request);
            System.out.println("arrayComment : "+arrayComment);
        } catch (CommentairesException e) {
            //setErreur( CHAMP_, e.getMessage() );
            System.out.println("pb");
        }
        return arrayComment;
    }


    /**
     * Permet de se connecter à la base de donnée.
     * @return MongoCollection<Document> qui permet ensuite de se connecter a la base de donnée et
     */
    private MongoCollection<Document> connexionMongoDB(){


        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase database = mongo.getDatabase("cinoxNoSqlDB");
        // Accessing the database
        MongoCollection<Document> collection = database.getCollection("commentaire");

        return collection;
    }

    private void getCritiques(HttpServletRequest request)throws CommentairesException {

        MongoCollection<Document> collection = this.connexionMongoDB();
        System.out.println("collection"+collection);
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("iterDoc : "+iterDoc);
        JSONParser parser = new JSONParser();
        int i = 1;

        Iterator it = iterDoc.iterator();

        System.out.println("it.hasNext()"+it.hasNext());
        while (it.hasNext()) {
            Document doc = (Document)it.next();
            System.out.println("TEST doc :"+doc.toJson());

            // TODO : faire test si pas de critiques et verifier aussi qu'on peut pas inserer des critiques null
           /* String critique = (String) doc.get("critique").toString();
            System.out.println("critique"+critique);
            String idFilm = (String) doc.get("idFilm").toString();
            System.out.println("idFilm"+idFilm);
            String idUser = (String) doc.get("idUser");
            System.out.println("idUser"+idUser);
            String date = doc.get("date").toString();
            System.out.println("date"+date);*/
            Object obj = null;
            try {
                obj = parser.parse(doc.toJson());
                JSONObject comment = (JSONObject) obj;
                testCommentaire(comment);
                System.out.println("comment = " + obj);
                testCommentaire(comment);
                arrayComment.add(comment);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (CommentairesException e) {
                setErreur(CHAMP_ERROR, e.getMessage());
                e.printStackTrace();
            }

            i++;// TODO delete

        }
        System.out.println("Nb de film  : " + i); // TODO delete
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

    public void addCritiquesFilm(String idUser, String idFilm, String commentaires){
        MongoCollection<Document> collection = this.connexionMongoDB();
        Date date = new Date();
        System.out.println("idUser : "+idUser+" idFilm : "+idFilm+" commentaires :"+commentaires);
        Document document = new Document()
                .append("idUser", idUser)
                .append("idFilm", idFilm)
                .append("critique", commentaires)
                .append("date", date.toString());
        collection.insertOne(document);
        // TODO a enlever.
        System.out.println("Document inserted successfully" + document);

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            System.out.println("MONGOTESTSWAG : " + it.next());
            i++;
        }

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