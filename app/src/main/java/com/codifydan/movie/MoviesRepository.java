package com.codifydan.movie;

import java.util.List;

import retrofit2.Call;

public class MoviesRepository {
    private MovieApiService movieApiService = MovieApi.create();

    public Call<Movie> getMovies(Integer year) {
        return movieApiService.getMovies(year);
    }
}
