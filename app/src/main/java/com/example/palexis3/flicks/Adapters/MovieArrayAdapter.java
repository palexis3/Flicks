package com.example.palexis3.flicks.Adapters;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palexis3.flicks.Models.Movie;
import com.example.palexis3.flicks.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder {
        TextView title;
        TextView overView;
        ImageView image;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get data item for position
        Movie movie = getItem(position);
        String image = "";

        ViewHolder viewHolder;
        // check the existing view being reused
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overView = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.idMovieImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overView.setText(movie.getOverView());
        viewHolder.image.setImageResource(0);

        //retrieving picture from third party library via orientation of screen
        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            image = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            image = movie.getBackdropPath();
        }

        Picasso.with(getContext()).load(image).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.image);

        //return the view
        return convertView;
    }
}