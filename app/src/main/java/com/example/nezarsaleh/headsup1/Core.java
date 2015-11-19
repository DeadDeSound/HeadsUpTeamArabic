package com.example.nezarsaleh.headsup1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Core {

    Context activity;

    public Core(Context activity){
        this.activity = activity;
    }

    //API key
    private String API_Key = "00563628f45262fc9ea62d52baab6e4a";

    //Domain
    private String Domain = "https://api.themoviedb.org";

    //API calls
    private String all_movies_url = Domain + "/3/discover/movie?api_key=" + API_Key;
    private String movies_pop_asc = Domain + "/3/discover/movie?api_key=" + API_Key + "&&sort_by=popularity.asc";
    private String movies_pop_des = Domain + "/3/discover/movie?api_key=" + API_Key + "&&sort_by=popularity.desc";

    //Images locations
    public String small_image_url = "http://image.tmdb.org/t/p/w185/";
    public String large_image_url = "http://image.tmdb.org/t/p/w500/";
    public String xlarge_image_url = "http://image.tmdb.org/t/p/w780/";


    private String connect(String url) throws IOException {
        String data;
        BufferedReader reader;
        URL url1 = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(2000);
        httpURLConnection.connect();

        InputStream inputStream = httpURLConnection.getInputStream();
        StringBuilder stringBuffer = new StringBuilder();
        assert inputStream != null;
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine())!=null){
            stringBuffer.append(line).append("/n");
        }
        data = stringBuffer.toString();
        return data;
    }

    public JSONObject getMovies() throws JSONException {
        JSONObject json = null;
        try {
            String response = connect(all_movies_url);
            if (!response.equals("0")){
                json = new JSONObject(response);
            }else {
                Log.d("getMovies", response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public JSONObject getMoviesAsc() throws JSONException {
        JSONObject json = null;
        try {
            if (!connect(all_movies_url).equals("0")){
                json = new JSONObject(connect(movies_pop_asc));
            }else {
                Toast.makeText(activity, "connection Error", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public JSONObject getMoviesDes() throws JSONException {
        JSONObject json = null;
        try {
            if (!connect(all_movies_url).equals("0")){
                json = new JSONObject(connect(movies_pop_des));
            }else {
                Toast.makeText(activity, "connection Error", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
