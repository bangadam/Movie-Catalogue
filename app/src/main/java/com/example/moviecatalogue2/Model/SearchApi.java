package com.example.moviecatalogue2.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("/3/search/movie")
    Call<SearchMovie> getSearchFilm(
            @Query("api_key") String Key,
            @Query("query") String name
    );

    @GET("/3/search/tv")
    Call<SearchTv> getSearchTv(
            @Query("api_key") String Key,
            @Query("query") String name
    );
}
