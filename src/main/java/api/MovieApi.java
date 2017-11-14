package api;

import beans.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovieApi {

    public static void main(String[] args) throws Exception
    {

        MovieApi themoviedb = new MovieApi();

        System.out.println("Test1 - Http GET request for API Now Playing");
        themoviedb.nowPlaying();
        System.out.println("Test2 - Http GET request for API Up Coming");
        themoviedb.upComing();
        System.out.println("Test3 - Http GET request for API Top Rated");
        themoviedb.topRated();
        System.out.println("Test4 - Http GET request for API Search Movies");
        themoviedb.getMovieWithKeywords("Star Wars");
        System.out.println("Test5 - Http GET request for API Movies Details");
        themoviedb.getMovieDetails("1891");

    }

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

    private ArrayList<Movie> getMovies(JSONArray movies, ArrayList list) throws JSONException{
        for (int i = 0; i< 4; i++ ){
            JSONObject movie = movies.getJSONObject( i );
            Movie playingMovie = new Movie();

            if (movie.has("id"))
                playingMovie.setId(movie.getInt("id"));
            if (movie.has("title"))
                playingMovie.setTitle(movie.getString("title"));
            if (movie.has("vote_average"))
                playingMovie.setVote_average(movie.getDouble("vote_average"));
            if (movie.has("poster_path"))
                playingMovie.setPoster_path(movie.getString("poster_path"));
            if (movie.has("release_date"))
                playingMovie.setRelease_date(movie.getString("release_date"));

            list.add(playingMovie);
            //TODO MongoDB

        }

        return list;
    }

    public ArrayList<Movie> nowPlaying() throws Exception
    {
        String url_nowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

        jsonObject = new JSONObject(getData(url_nowPlaying).toString());

        if ( jsonObject.getJSONArray("results").length() >0){
            ArrayList<Movie> nowPlayingList = new ArrayList<>();
            nowPlayingList = getMovies(jsonObject.getJSONArray( "results" ), nowPlayingList);
            return nowPlayingList;
        }
        return null;
    }

    public ArrayList<Movie> upComing() throws Exception
    {
        String url_upComing = "https://api.themoviedb.org/3/movie/upcoming?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

        jsonObject = new JSONObject(getData(url_upComing).toString());

        if ( jsonObject.getJSONArray("results").length() >0){
            ArrayList<Movie> upComingList = new ArrayList<>();
            upComingList = getMovies(jsonObject.getJSONArray( "results" ), upComingList);
            return upComingList;
        }
        return null;
    }


    public ArrayList<Movie> topRated() throws Exception
    {
        String url_topRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=37558deaca34c291c832573f6b749f63&language=fr&page=1&region=fr";

        jsonObject = new JSONObject(getData(url_topRated).toString());
        if ( jsonObject.getJSONArray("results").length() >0){

            ArrayList<Movie> topRatedList = new ArrayList<>();
            topRatedList = getMovies(jsonObject.getJSONArray( "results" ), topRatedList);
            return topRatedList;
        }
        return null;
    }




    public ArrayList<Movie> getMovieWithKeywords(String keywords) throws Exception
    {
        //TODO replace
        keywords = keywords.replace(" ","%");
        String url_keywords = "https://api.themoviedb.org/3/search/movie?api_key=37558deaca34c291c832573f6b749f63&language=fr&query="+keywords+"&page=1&include_adult=false";

        jsonObject = new JSONObject(getData(url_keywords).toString());
        if ( jsonObject.getJSONArray("results").length() >0) {
            ArrayList<Movie> movies_keywords = new ArrayList<>();
            movies_keywords = getMovies(jsonObject.getJSONArray("results"), movies_keywords);
            return movies_keywords;
        }
        return null;
    }


    public Movie getMovieDetails(String id) throws Exception{

        String url_details = "https://api.themoviedb.org/3/movie/"+id+"?api_key=37558deaca34c291c832573f6b749f63&language=fr&append_to_response=fr";
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

        System.out.println(response.toString());

        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            System.out.println(jsonObject.length());

            if (jsonObject.length() > 0) {
                Movie movie = new Movie();
                movie.setPoster_path(jsonObject.getString("poster_path"));
                movie.setRelease_date(jsonObject.getString("release_date"));
                movie.setOverview(jsonObject.getString("overview"));
                return movie;
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
