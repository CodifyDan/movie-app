package com.codifydan.movie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codifydan.movie.Model.Movie;
import com.codifydan.movie.UI.MovieAdapter;
import com.codifydan.movie.UI.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ButtonClickListener, RecyclerView.OnItemTouchListener {

    RecyclerView rvMovies;
    MovieAdapter mMovieAdapter;
    private List<Movie> mMoviesList;
    MoviesViewModel viewModel;
    Button mSearchMoviesBtn;
    TextView mYearInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovies = findViewById(R.id.rvMovies);
        mSearchMoviesBtn = findViewById(R.id.search_movies_btn);
        mYearInput = findViewById(R.id.year_input);
        mMoviesList = new ArrayList<>();

        mMovieAdapter = new MovieAdapter(mMoviesList, this);
        rvMovies.setAdapter(mMovieAdapter);
        setupGrid();

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });


        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mMoviesList.clear();
                mMoviesList.addAll(movies);
                mMovieAdapter.notifyDataSetChanged();
            }
        });

        mSearchMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getMovies(Integer.parseInt(mYearInput.getText().toString()));
            }
        });
    }


    private void setupGrid() {

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        rvMovies.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        gridLayoutManager.setSpanCount(calculateSpanCount());
                        gridLayoutManager.requestLayout();
                    }
                });
    }

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width.
     *
     * @return int number of spans.
     */

    private int calculateSpanCount() {

        int viewWidth = rvMovies.getMeasuredWidth();
        float cardViewWidth = getResources().getDimension(R.dimen.poster_width);
        int spanCount = (int) Math.floor(viewWidth / (cardViewWidth + 16));


        return spanCount >= 1 ? spanCount : 1;

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public void onSelect(Movie movie) {
        Intent i = new Intent(this, MovieDetail.class);
        i.putExtra("Movie", movie);
        startActivity(i);
    }
}
