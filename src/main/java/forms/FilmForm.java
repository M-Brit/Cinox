package forms;

import dao.FilmImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class FilmForm {

        JSONObject jsonObject;

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


        public JSONArray nowPlaying() throws Exception
        {
            String url_nowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            jsonObject = new JSONObject(getData(url_nowPlaying).toString());
            return jsonObject.getJSONArray( "results" );
        }

        public JSONArray upComing() throws Exception
        {
            String url_upComing = "https://api.themoviedb.org/3/movie/upcoming?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            jsonObject = new JSONObject(getData(url_upComing).toString());
            return jsonObject.getJSONArray("results");
        }


        public JSONArray topRated() throws Exception
        {
            String url_topRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

            jsonObject = new JSONObject(getData(url_topRated).toString());
            return jsonObject.getJSONArray("results");
        }

        public JSONArray getMovieWithKeywords(String keywords) throws Exception
        {
            //TODO replace
            keywords = keywords.replace(" ","%");
            String url_keywords = "https://api.themoviedb.org/3/search/movie?api_key=37558deaca34c291c832573f6b749f63&language=fr&query="+keywords+"&page=1&include_adult=false";

            jsonObject = new JSONObject(getData(url_keywords).toString());
            if ( jsonObject.getJSONArray("results").length() >0) {
                return jsonObject.getJSONArray("results");
            }
            return null;
        }

        public JSONArray getMovieByTypeId(int filmTypeId) throws Exception{
            String url_fileType = "https://api.themoviedb.org/3/genre/"+ filmTypeId +"/movies?api_key=37558deaca34c291c832573f6b749f63&language=fr&include_adult=false&sort_by=created_at.asc";
            jsonObject = new JSONObject(getData(url_fileType).toString());
            if ( jsonObject.getJSONArray("results").length() >0) {
                return jsonObject.getJSONArray("results");
            }
            return null;
        }

        public JSONObject getMovieDetails(String id) throws Exception{
            // https://www.youtube.com/watch?v=ePbKGoIGAXY

            String url_details = "https://api.themoviedb.org/3/movie/"+id+"?api_key=37558deaca34c291c832573f6b749f63&append_to_response=videos&language=fr";
            URL url = new URL(url_details);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            //TODO : acteurs
            /*String url_actors = " https://api.themoviedb.org/3/movie/284053/credits?api_key=37558deaca34c291c832573f6b749f63";
            URL urlactors = new URL(url_actors);
            HttpURLConnection connectionActors = (HttpURLConnection) urlactors.openConnection();
            connectionActors.setRequestMethod("GET");
            BufferedReader inactors = new BufferedReader(
                    new InputStreamReader(connectionActors.getInputStream()));
            String inputLineActors;
            StringBuffer responseActors = new StringBuffer();
            while ((inputLineActors = inactors.readLine()) != null) {
                responseActors.append(inputLineActors);
            }
            inactors.close();*/
            //TODO : FIN acteurs


            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                return jsonObject;
                //TODO acteurs
                /*JSONObject jsonObjectActors = new JSONObject(responseActors.toString());
                JSONArray jsonArrayFILM = new JSONArray();
                jsonArrayFILM.put(jsonObject);
                jsonArrayFILM.put(jsonObjectActors);*/
                //TODO FIN acteurs
                /*FilmImpl fi = new FilmImpl();
                //  mise en bdd des films
                fi.ajoutFilm(jsonArrayFILM);
                //  mise en bdd des films
                fi.ajoutActeurs(jsonObject);
                return jsonArrayFILM;*/

            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;

        }





    }

