package com.example.palexis3.flicks;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class QuickPlayActivity extends YouTubeBaseActivity {

    private static final String API_KEY = "f1e55cc01616b64d1b66566ca00d707a";
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        id = getIntent().getStringExtra("id");


        youTubePlayerView.initialize(API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        final String url = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=%s", id, API_KEY);
                        AsyncHttpClient client = new AsyncHttpClient();
                        final YouTubePlayer yTubePlayer = youTubePlayer;

                        //making a request call with id to hit videos endpoint to retrieve the trailer
                        client.get(url, new JsonHttpResponseHandler() {
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                JSONArray JsonResults;
                                JSONObject JsonObject;
                                try {
                                    JsonResults = response.getJSONArray("results");
                                    JsonObject = JsonResults.getJSONObject(0);
                                    String key = JsonObject.getString("key");
                                    yTubePlayer.loadVideo(key);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                            }
                        });
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(QuickPlayActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}