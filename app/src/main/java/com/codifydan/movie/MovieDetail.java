package com.codifydan.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codifydan.movie.Model.Movie;

public class MovieDetail extends AppCompatActivity {

    ImageView mBackdropImage;
    ImageView mPosterImage;
    TextView mTitle;
    TextView mDescription;
    TextView mRating;
    TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mBackdropImage = findViewById(R.id.detail_backdrop_image);
        mPosterImage = findViewById(R.id.detail_poster_image);
        mTitle = findViewById(R.id.detail_title);
        mDescription = findViewById(R.id.detail_overview_text);
        mRating = findViewById(R.id.detail_rating);
        mReleaseDate = findViewById(R.id.detail_release_date);

        Movie movie = (Movie) getIntent().getSerializableExtra("Movie");
        setTitle(movie.getTitle());

        Glide.with(getBaseContext()).load("https://image.tmdb.org/t/p/original" + movie.getBackdrop()).into(mBackdropImage);
        Glide.with(getBaseContext()).load("https://image.tmdb.org/t/p/original" + movie.getPoster()).into(mPosterImage);
        mTitle.setText(movie.getTitle());
        mDescription.setText(movie.getOverview());
        mRating.setText(movie.getRating().toString());
        mReleaseDate.setText(movie.getReleaseDate());

    }
}
