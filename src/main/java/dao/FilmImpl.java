package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import exceptions.FilmException;
import forms.FilmForm;
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




    public void ajoutFilm(JSONArray arrayFilm) throws FilmException, Exception {

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
        JSONObject filmDetails;
        Document doc;
        Document document;
        FilmForm filmForm = new FilmForm();
        String video;

        if(iterator.hasNext()){
            for( int j = 0; j < arrayFilm.length(); j++) {
                //TODO it  false
                it = iterator;

                jsonObject = (JSONObject) arrayFilm.get(j);
                int id = jsonObject.getInt("id");

                int conflit = 0;
                System.out.println("it=="+ it.hasNext());
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
                    filmDetails = filmForm.getMovieDetails(String.valueOf(jsonObject.getInt("id")));

                    System.out.println("jsonObject1"+ filmDetails);
                    document = new Document();

                    document.put("id", jsonObject.getInt("id"));
                    document.put("title", jsonObject.getString("title"));
                    document.put("poster_path", jsonObject.getString("poster_path"));
                    document.put("vote_average", jsonObject.getDouble("vote_average"));
                    document.put("overview", jsonObject.getString("overview"));
                    //TODO
                    document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));
                    if((filmDetails.getJSONObject("videos").getJSONArray("results")).length() != 0){
                        video = ((JSONObject)(filmDetails.getJSONObject("videos").getJSONArray("results")).get(0)).getString("key");
                    }else {
                        System.out.println("null");
                        video= "";
                    }
                    document.put("videos", video);

                    collection.insertOne(document);
                }

            }
        }else {

            for( int j = 0; j < arrayFilm.length(); j++) {
                jsonObject = (JSONObject) arrayFilm.get(j);
                filmDetails = filmForm.getMovieDetails(String.valueOf(jsonObject.getInt("id")));

                System.out.println("jsonObject1"+ filmDetails);
                document = new Document();

                document.put("id", jsonObject.getInt("id"));
                document.put("title", jsonObject.getString("title"));
                document.put("poster_path", jsonObject.getString("poster_path"));
                document.put("vote_average", jsonObject.getDouble("vote_average"));
                document.put("overview", jsonObject.getString("overview"));
                //TODO
                document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));

                System.out.println("test=="+ (filmDetails.getJSONObject("videos").getJSONArray("results")).length());
                if((filmDetails.getJSONObject("videos").getJSONArray("results")).length() != 0){
                    video = ((JSONObject)(filmDetails.getJSONObject("videos").getJSONArray("results")).get(0)).getString("key");
                }else {
                    System.out.println("null");
                    video= "";
                }
                document.put("videos", video);

                collection.insertOne(document);


                /*document = new Document();

                document.put("id", jsonObject.getInt("id"));
                document.put("title", jsonObject.getString("title"));
                document.put("poster_path", jsonObject.getString("poster_path"));
                document.put("vote_average", jsonObject.getDouble("vote_average"));
                document.put("overview", jsonObject.getString("overview"));
                document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));

                collection.insertOne(document);*/

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
