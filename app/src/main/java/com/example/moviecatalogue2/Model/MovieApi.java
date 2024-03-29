package com.example.moviecatalogue2.Model;

import com.example.moviecatalogue2.Model.Movie;
import com.example.moviecatalogue2.Model.SearchMovieResults;
import com.example.moviecatalogue2.Model.Tv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Call<Movie> getMoviePopular(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<Tv> getTvPopular(@Query("api_key") String apiKey);
}
