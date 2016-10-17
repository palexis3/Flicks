package com.example.palexis3.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends AppCompatActivity {

    String poster_image;
    String overview;
    String date;
    String title;
    String id;

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
        id = getIntent().getStringExtra("id");

        //initialize our detail views
        titleView = (TextView) findViewById(R.id.titleDetailView);
        dateView = (TextView) findViewById(R.id.dateDetailView);
        overviewView = (TextView) findViewById(R.id.overviewDetailView);
        image = (ImageView) findViewById(R.id.movieImageDetailView);

        //populate views with data
        titleView.setText(title);
        //parsing date
        String[] arr = date.split("-");
        dateView.setText("Release Date: " + arr[1] + "-" + arr[2] + "-" + arr[0]);

        overviewView.setText(overview);
        Picasso.with(this).load(poster_image).into(image);

        //user has clicked on image to play trailer
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //having some difficulty with obtaining key b/c request call within the imageview clickable resource doesn't want to function
                Intent i = new Intent(MovieDetailActivity.this, QuickPlayActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }
}
