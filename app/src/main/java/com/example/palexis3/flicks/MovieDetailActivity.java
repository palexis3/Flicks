package com.example.palexis3.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    String poster_image;
    String overview;
    String date;
    String title;

    ImageView image;
    TextView titleView;
    TextView dateView;
    RatingBar rate;
    TextView overviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //get data from intent
        poster_image = getIntent().getStringExtra("poster_image");
        overview = getIntent().getStringExtra("overview");
        date = getIntent().getStringExtra("release_date");
        title = getIntent().getStringExtra("title");

        //initialize our detail views
        titleView = (TextView) findViewById(R.id.titleDetailView);
        dateView = (TextView) findViewById(R.id.dateDetailView);
        overviewView = (TextView) findViewById(R.id.overviewDetailView);
        image = (ImageView) findViewById(R.id.movieImageDetailView);

        //populate views with data
        titleView.setText(title);
        dateView.setText(date);
        overviewView.setText(overview);
        Picasso.with(this).load(poster_image).into(image);

    }
}
