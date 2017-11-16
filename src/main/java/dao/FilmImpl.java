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


    private void saveMongoDB(JSONObject jsonObject , MongoCollection<Document> collection) throws Exception{
        JSONObject filmDetails;
        JSONObject getActors;
        Document document;
        FilmForm filmForm = new FilmForm();
        String video;

            filmDetails = filmForm.getMovieDetails(String.valueOf(jsonObject.getInt("id")));
            getActors = filmForm.getActors(String.valueOf(jsonObject.getInt("id")));

            document = new Document();
            document.put("id", jsonObject.getInt("id"));
            document.put("title", jsonObject.getString("title"));
            document.put("poster_path", jsonObject.getString("poster_path"));
            document.put("vote_average", jsonObject.getDouble("vote_average"));
            document.put("overview", jsonObject.getString("overview"));
            document.put("genre_ids", jsonObject.getJSONArray("genre_ids"));

            if((filmDetails.getJSONObject("videos").getJSONArray("results")).length() != 0){
                video = ((JSONObject)(filmDetails.getJSONObject("videos").getJSONArray("results")).get(0)).getString("key");
            }else {
                video= "";
            }
            document.put("videos", video);

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
