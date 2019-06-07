package com.codifydan.movie.UI;

import com.codifydan.movie.Network.MovieApi;
import com.codifydan.movie.Network.MovieApiService;
import com.codifydan.movie.Model.MovieResponse;

import retrofit2.Call;

public class MoviesRepository {
    private MovieApiService movieApiService = MovieApi.create();

    public Call<MovieResponse> getMovies(Integer year) {
        return movieApiService.getMovies(year);
    }
}
