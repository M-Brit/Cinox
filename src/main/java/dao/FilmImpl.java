package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import exceptions.FilmException;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
        JSONParser parser = new JSONParser();
        int i = 1;
        Iterator it = iterDoc.iterator();

        for(int j=0; j< arrayFilm.length(); j++) {
            int idFIlm= arrayFilm.getJSONObject(j).getInt("id");
            String nameFIlm= arrayFilm.getJSONObject(j).getString("title");
            //arrayFilm[j].id;

            while (it.hasNext()) {
                //System.out.println("MONGOTESTSWAG : " + it.next());
                Document doc = (Document)it.next();
                Object obj = null;
                String film = "";
                String name ="";
                try {
                    obj = parser.parse(doc.toJson());
                    System.out.println("eeeee"+ obj);
                    JSONObject jsonfilm = (JSONObject) obj;
                    System.out.println("uuuuuu");
                    film = jsonfilm.getString("id");
                    System.out.println("rr"+ film);
                    name = jsonfilm.getString("name");
                    System.out.println("rr"+ name);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                               //TODO : test
                if (String.valueOf(idFIlm).equals(film) || nameFIlm.equals(name) )
                    throw new FilmException("");
            }

            Document document = new Document()
                    .append("id", idFIlm)
                    .append("name", nameFIlm)
                    .append("film", arrayFilm.getJSONObject(j));
            collection.insertOne(document);

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
