package com.codifydan.movie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String jsonString = response.body().toString();
                            JSONObject mainObject = null;
                            try {
                                mainObject = new JSONObject(jsonString);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Type listType = new TypeToken<List<Movie>>() {}.getType();
                            List<Movie> yourList = null;
                            try {
                                yourList = new Gson().fromJson(mainObject.getJSONArray("results").toString(), listType);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            movies.setValue(yourList);
                        } else {
                            error.setValue("Api Error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
                });
    }
}
