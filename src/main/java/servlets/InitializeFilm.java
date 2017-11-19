package servlets;

import dao.FilmImpl;
import forms.FilmForm;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/*
* TODO: save to MongoDB
* */
public class InitializeFilm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*  System.out.println("Test1 - Http GET request for API Now Playing");
            themoviedb.nowPlaying();
            System.out.println("Test2 - Http GET request for API Up Coming");
            themoviedb.upComing();
            System.out.println("Test3 - Http GET request for API Top Rated");
            themoviedb.topRated();
            System.out.println("Test4 - Http GET request for API Search Movies");
            themoviedb.getMovieWithKeywords("Star Wars");
            System.out.println("Test5 - Http GET request for API Movies Details");
            themoviedb.getMovieDetails("1891");
            System.out.println("Test6 - Http GET request for API Now Playing");
            JSONArray listFilms = themoviedb.getMovieByTypeId(28);
        */

        FilmForm themoviedb = new FilmForm();
        FilmImpl filmImpl = new FilmImpl();

        try {
            //now playing
            JSONArray listFilms = themoviedb.nowPlaying();
            System.out.println("nowPlaying=="+ listFilms.length());
            filmImpl.ajoutFilm("nowPlaying", listFilms);

            //up coming
            listFilms = themoviedb.upComing();
            System.out.println("upComing=="+ listFilms.length());
            filmImpl.ajoutFilm("upComing", listFilms);

            //popular
            listFilms = themoviedb.popular();
            System.out.println("popular=="+ listFilms.length());
            filmImpl.ajoutFilm("popular", listFilms);

            //image
            listFilms = filmImpl.findAllFilm();
            JSONObject jsonObject;

            String savaPath = "images/";
            for(int i= 0; i< 2; i++){
                jsonObject = (JSONObject) listFilms.get(i);
                String imagePath = "https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path");
                System.out.println("imagePath=="+ imagePath);

                byte[] btImg = getImageFromNetByUrl(imagePath);
                if(null != btImg && btImg.length > 0){
                    writeImageToDisk(btImg, savaPath, String.valueOf(jsonObject.getInt("id")));
                }else{
                    System.out.println("");
                }
            }





        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void writeImageToDisk(byte[] img, String savaPath, String fileName){
        try {
            File file = new File(fileName+".jpg");
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
