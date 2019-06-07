package com.codifydan.movie.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.codifydan.movie.Model.Movie;
import com.codifydan.movie.Model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends AndroidViewModel {
    private MoviesRepository moviesRepository = new MoviesRepository();
    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MoviesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void getMovies(Integer year) {
        moviesRepository
                .getMovies(year)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            MovieResponse movieResponse = response.body();
                            List<Movie> movieList = movieResponse.getMovies();

                            movies.setValue(movieList);
                        } else {
                            error.setValue("Api Error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
                });
    }
}
