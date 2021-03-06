package com.example.palexis3.flicks.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w300/%s", backdropPath);
    }

    public String getOverView() {
        return overView;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getReleaseDate() { return releaseDate; }

    public String getId() { return id; }

    public int getPopularity() { return (int) Math.round(popularity); }

    String posterPath;
    String originalTitle;
    String overView;
    String backdropPath;
    String releaseDate;
    String id;
    double popularity;


    public Movie(JSONObject jsonObject) throws JSONException{

        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getString("id");
        this.popularity = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}