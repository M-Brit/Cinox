package forms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * code metier qui gerer toute les requetes a l'api moviedb
 */
public class FilmForm {
        //TODO global ou local
        //JSONObject jsonObject;

        private StringBuffer getData(String url) throws Exception{
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response;

        }

        /**
         * Permet d'obtenir les films qui sont actuellement sortie depuis l'api moviedb.
         * @return
         * @throws Exception
         */
        public JSONArray nowPlaying() throws Exception
        {
            String url_nowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            JSONObject jsonObject = new JSONObject(getData(url_nowPlaying).toString());
            return jsonObject.getJSONArray( "results" );
        }

        /**
         * Permet d'obtenir les films qui vont prochainement sortir depuis l'api moviedb.
         * @return
         * @throws Exception
         */
        public JSONArray upComing() throws Exception
        {
            String url_upComing = "https://api.themoviedb.org/3/movie/upcoming?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            JSONObject jsonObject = new JSONObject(getData(url_upComing).toString());
            return jsonObject.getJSONArray("results");
        }


        public JSONArray topRated() throws Exception
        {
            String url_topRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            JSONObject jsonObject = new JSONObject(getData(url_topRated).toString());
            return jsonObject.getJSONArray("results");
        }

        /**
         * Recherche les film populaire c'est a dire les plus apprecier des clients de l'api moviedb.
         * @return
         * @throws Exception
         */
        public JSONArray popular() throws Exception
        {
            String url_popular = "https://api.themoviedb.org/3/movie/popular?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            JSONObject jsonObject = new JSONObject(getData(url_popular).toString());
            return jsonObject.getJSONArray("results");
        }

        /**
         * Obtenir des films avec des mot cles pour la recherche de film
         * @param keywords mot cles qui permet la recherche de film
         * @return
         * @throws Exception
         */
        public JSONArray getMovieWithKeywords(String keywords) throws Exception
        {
            //TODO replace
            keywords = keywords.replace(" ","%");
            String url_keywords = "https://api.themoviedb.org/3/search/movie?api_key=37558deaca34c291c832573f6b749f63&language=fr&query="+keywords+"&page=1&include_adult=false";

            JSONObject jsonObject = new JSONObject(getData(url_keywords).toString());
            if ( jsonObject.getJSONArray("results").length() >0) {
                return jsonObject.getJSONArray("results");
            }
            return null;
        }

        /**
         * Permet d'obtenir les films par rapport a un category depuis l'API movieDB
         * @param filmTypeId id de la categorie
         * @return
         * @throws Exception
         */
        public JSONArray getMovieByTypeId(int filmTypeId) throws Exception{
            String url_fileType = "https://api.themoviedb.org/3/genre/"+ filmTypeId +"/movies?api_key=37558deaca34c291c832573f6b749f63&language=fr&include_adult=false&sort_by=created_at.asc";
            JSONObject jsonObject = new JSONObject(getData(url_fileType).toString());
            if ( jsonObject.getJSONArray("results").length() >0) {
                return jsonObject.getJSONArray("results");
            }
            return null;
        }

        /**
         * Permet d'obtenir le detail  d'un film depuis l'API movieDB
         * @param id id du film
         * @return
         * @throws Exception
         */
        public JSONObject getMovieDetails(String id) throws Exception{
            // https://www.youtube.com/watch?v=ePbKGoIGAXY

            String url_details = "https://api.themoviedb.org/3/movie/"+id+"?api_key=37558deaca34c291c832573f6b749f63&append_to_response=videos&language=fr";
            try {
                JSONObject jsonObject = new JSONObject(getData(url_details).toString());
                return jsonObject;
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;

        }

        /**
         * Permet d'obtenir les acteurs d'un film depuis l'API movieDB
         * @param id de l'acteurs en question
         * @return
         */
        public JSONObject getActors(String id){
            try {
                String url_actors = " https://api.themoviedb.org/3/movie/"+id+ "/credits?api_key=37558deaca34c291c832573f6b749f63";

                JSONObject jsonObject = new JSONObject(getData(url_actors).toString());

                return jsonObject;
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;

        }

    }

