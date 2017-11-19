package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import forms.FilmForm;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.HibernateUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

public class FilmImpl  {

    private MongoCollection<Document> connexionMongoDB(){

        // Accessing the database
        MongoCollection<Document> collection = HibernateUtil.database.getCollection("films");

        return collection;
    }

    //TODO verifier doublon
    public void ajoutFilm(String type, JSONArray arrayFilm) throws Exception {

        MongoCollection<Document> collection = this.connexionMongoDB();
        FindIterable<Document> iterDoc = collection.find();
        Iterator iterator = iterDoc.iterator();
        Iterator it;

        int filmId ;
        String filmName ="";

        JSONObject jsonObject;
        JSONObject object;
        Document doc;

        if(iterator.hasNext()){
            for( int j = 0; j < arrayFilm.length(); j++) {
                //TODO it  false
                it = iterator;

                jsonObject = (JSONObject) arrayFilm.get(j);
                int id = jsonObject.getInt("id");

                int conflit = 0;
                while (it.hasNext()) {
                    doc = (Document) it.next();
                    object = new JSONObject(doc.toJson());
                    filmId = object.getInt("id");
                    filmName = object.getString("title");
                    if (id == filmId || jsonObject.getString("title").equals(filmName)) {
                        //TODO throw new FilmException("");
                        System.out.println("conflit");
                        conflit ++;
                        break;
                    }
                }
                if(conflit == 0) {
                    saveMongoDB(type, jsonObject, collection);
                }

            }
        }else {
            for(int j= 0; j< arrayFilm.length(); j++){
                jsonObject = (JSONObject)arrayFilm.get(j);
                saveMongoDB(type, jsonObject, collection);
            }

        }
    }

    public JSONArray findAllFilm(){
        MongoCollection<Document> collection = this.connexionMongoDB();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> mongoCursor = documents.iterator();

        JSONArray array = new JSONArray();
        JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new JSONObject(doc.toJson());
            array.put(object);
        }
        //TODO
        //close mongoDB connextion;
        return  array;
    }


    public JSONObject findFilmById(String id) throws Exception {
        MongoCollection<Document> collection = this.connexionMongoDB();

        BasicDBObject query = new BasicDBObject();
        query.put("id", Integer.valueOf(id));

        FindIterable<Document> documents = collection.find(query);
        Iterator it = documents.iterator();

        JSONObject object= new JSONObject();
        if(it.hasNext()){
            Document doc = (Document) it.next();
            object = new JSONObject(doc.toJson());

        }
        return object;

    }

    public JSONArray searchByKeyword(String keywords){
        //keywords = keywords.replace(" ","%");
        MongoCollection<Document> collection = this.connexionMongoDB();
        BasicDBObject query = new BasicDBObject();
        //Pattern regex = Pattern.compile(keywords);
        Pattern regex = Pattern.compile(keywords,Pattern.CASE_INSENSITIVE);
        query.put("title", regex);
        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> mongoCursor = documents.iterator();
        JSONArray array = new JSONArray();
        JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new JSONObject(doc.toJson());
            if(array.length() > 0){
                for(Object o: array){
                    if(object.getInt("id") != ((JSONObject)o).getInt("id")){
                        array.put(object);
                    }
                }
            }else {
                array.put(object);
            }

        }
        //TODO
        //close mongoDB connextion;
        return  array;
    }

    public JSONArray getByCategory(int categoryId){
        MongoCollection<Document> collection = this.connexionMongoDB();
        BasicDBObject query = new BasicDBObject();
        query.put("genre_ids", new BasicDBObject("$in",Arrays.asList(categoryId)));

        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> mongoCursor = documents.iterator();
        JSONArray array = new JSONArray();
        JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new JSONObject(doc.toJson());
            array.put(object);
        }
        return  array;
    }

    public JSONArray findFilmByType(String filmType) throws Exception {
        MongoCollection<Document> collection = this.connexionMongoDB();

        BasicDBObject query = new BasicDBObject();
        query.put("type", filmType);
         /*TODO optimiser
         if("upComing".equals(filmType)){
            query.put("release_date", new BasicDBObject("$gte", new Date()));
        }*/

        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> mongoCursor = documents.iterator();
        JSONArray array = new JSONArray();
        JSONObject object;
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            object = new JSONObject(doc.toJson());
            array.put(object);
        }
        return  array;
    }


    private void saveMongoDB(String type, JSONObject jsonObject , MongoCollection<Document> collection) throws Exception{
        JSONObject filmDetails;
        JSONObject getActors;
        Document document;
        FilmForm filmApiForm = new FilmForm();
        String video;

            filmDetails = filmApiForm.getMovieDetails(String.valueOf(jsonObject.getInt("id")));
            getActors = filmApiForm.getActors(String.valueOf(jsonObject.getInt("id")));

            document = new Document();
            document.put("type", type);
            document.put("id", jsonObject.getInt("id"));
            document.put("title", jsonObject.getString("title"));
            document.put("poster_path", jsonObject.getString("poster_path"));
            document.put("vote_average", jsonObject.getDouble("vote_average"));
            document.put("overview", jsonObject.getString("overview"));
            document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));
            document.put("overview", jsonObject.getString("overview"));
            document.put("release_date", jsonObject.getString("release_date"));

        if((filmDetails.getJSONObject("videos").getJSONArray("results")).length() != 0){
                video = ((JSONObject)(filmDetails.getJSONObject("videos").getJSONArray("results")).get(0)).getString("key");
            }else {
                video= "";
            }
            document.put("video", video);

            JSONArray array = getActors.optJSONArray("cast");
            JSONArray acteurs = new JSONArray();

            //upComming, il y a que infos de zero ou trois quatre acteurs, on affiche que 3 aucteur
            //nowPlaying et popular, on affiche 5 acteurs
            int length = 5;
            if("upComing".equals(type) && array.length()>3){
                length=3;
            } else {
                length=array.length();
            }

            if(array != null && array.length()!= 0) {
                int ac =0;
                for(int a= 0; a< length; a++){
                    if(!("N/A".equals(((JSONObject)array.get(a)).optString("profile_path", "N/A"))) && ac< 5){
                        String actorName = array.optJSONObject(a).optString("name", "N/A");
                        String actorPicture = array.optJSONObject(a).optString("profile_path", "N/A");
                        acteurs.put(actorName + actorPicture);
                        ac++;
                    }
                }
            }
            document.put("acteurs", acteurs);
            collection.insertOne(document);
    }


}
