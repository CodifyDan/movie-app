package com.codifydan.movie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("backdrop_path")
    String backdrop;
    @SerializedName("poster_path")
    String poster;
    @SerializedName("title")
    String title;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("vote_average")
    Double rating;
    @SerializedName("overview")
    String overview;

    public Movie(String backdrop, String poster, String title, String releaseDate, Double rating, String overview) {
        this.backdrop = backdrop;
        this.poster = poster;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
