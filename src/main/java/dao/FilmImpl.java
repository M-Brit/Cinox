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

    public void ajoutFilm(JSONArray arrayFilm) throws Exception {

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
                    saveMongoDB(jsonObject, collection);
                }

            }
        }else {
            for(int j= 0; j< arrayFilm.length(); j++){
                jsonObject = (JSONObject)arrayFilm.get(j);
                saveMongoDB(jsonObject, collection);
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
        Pattern regex = Pattern.compile(keywords);
        System.out.println("regex=="+ regex);
        query.put("title", regex);
        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> mongoCursor = documents.iterator();
        System.out.println("it=="+ mongoCursor.hasNext());
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
            System.out.println("object=="+ object);
            array.put(object);
        }
        return  array;
    }




    private void saveMongoDB(JSONObject jsonObject , MongoCollection<Document> collection) throws Exception{
        JSONObject filmDetails;
        JSONObject getActors;
        Document document;
        FilmForm filmApiForm = new FilmForm();
        String video;

            filmDetails = filmApiForm.getMovieDetails(String.valueOf(jsonObject.getInt("id")));
            getActors = filmApiForm.getActors(String.valueOf(jsonObject.getInt("id")));

            document = new Document();
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
            if(array != null) {
                int ac =0;
                for(int a= 0; a< 5; a++){
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
