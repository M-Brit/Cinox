package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import exceptions.FilmException;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.HibernateUtil;

import java.util.Iterator;

public class FilmImpl  {


    public static void traitementMongoFilm(String nom, String prenom, String pseudo, String motdepasse, String email) {

    }

    private MongoCollection<Document> connexionMongoDB(){

        // Accessing the database
        MongoCollection<Document> collection = HibernateUtil.database.getCollection("films");

        return collection;
    }
        //collection.insertOne(document);




    public void ajoutFilm(JSONArray arrayFilm) throws FilmException {

        MongoCollection<Document> collection = this.connexionMongoDB();
        System.out.println("collection"+collection);
        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("iterDoc : "+iterDoc);
        //JSONParser parser = new JSONParser();

        Iterator iterator = iterDoc.iterator();
        Iterator it;

        int filmId ;
        String filmName ="";

        JSONObject jsonObject;
        JSONObject object;
        Document doc;
        Document document;
        System.out.println("arrayFilm =="+ arrayFilm.length());

        if(iterator.hasNext()){
            for( int j = 0; j < arrayFilm.length(); j++) {
                jsonObject = (JSONObject) arrayFilm.get(j);
                int id = jsonObject.getInt("id");

                it = iterator;
                int conflit = 0;
                while (it.hasNext()) {
                    doc = (Document) it.next();
                    object = new JSONObject(doc.toJson());
                    filmId = object.getInt("id");
                    filmName = object.getString("title");
                    if (id == filmId || jsonObject.getString("title").equals(filmName)) {
                        //throw new FilmException("");
                        System.out.println("conflit");
                        conflit ++;
                        break;
                    }
                }
                if(conflit == 0) {
                    System.out.println("document");
                    document = new Document();

                    document.put("id", jsonObject.getInt("id"));
                    document.put("title", jsonObject.getString("title"));
                    document.put("poster_path", jsonObject.getString("poster_path"));
                    document.put("vote_average", jsonObject.getDouble("vote_average"));
                    document.put("overview", jsonObject.getString("overview"));
                    document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));

                    collection.insertOne(document);
                }

            }
        }else {

            for( int j = 0; j < arrayFilm.length(); j++) {
                jsonObject = (JSONObject) arrayFilm.get(j);
                document = new Document();

                document.put("id", jsonObject.getInt("id"));
                document.put("title", jsonObject.getString("title"));
                document.put("poster_path", jsonObject.getString("poster_path"));
                document.put("vote_average", jsonObject.getDouble("vote_average"));
                document.put("overview", jsonObject.getString("overview"));
                document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));

                //document.put("film", (JSONObject)jsonObject);
                collection.insertOne(document);

            }

        }
    }

    public FilmImpl ajoutActeurs(JSONObject jsonobj){

        MongoCollection<Document> collection = this.connexionMongoDB();

        Document document = new Document()
                .append("acteurs" , jsonobj);
        collection.insertOne(document);

        return null;
    }
}
