package com.codifydan.movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("3/discover/movie?api_key=5f6fc3b715495010effdf0c425cd29ba&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false&page=1")
    Call<Movie> getMovies(@Query(value = "primary_release_year", encoded = true) Integer year);
}
