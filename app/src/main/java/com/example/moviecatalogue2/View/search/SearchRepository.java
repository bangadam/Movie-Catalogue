package com.example.moviecatalogue2.View.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.moviecatalogue2.Model.SearchMovie;
import com.example.moviecatalogue2.Model.SearchApi;
import com.example.moviecatalogue2.Model.SearchTv;
import com.example.moviecatalogue2.View.main.MovieClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {

    private static SearchRepository searchRepository;
    private SearchApi searchApi;
    public static SearchRepository getInstance() {
        if (searchRepository == null) {
            searchRepository = new SearchRepository();
        }

        return searchRepository;
    }

    public SearchRepository() {
        searchApi = MovieClient.createService(SearchApi.class);
    }


    public MutableLiveData<SearchMovie> getSearchMovie(String apiKey, String query) {
        final MutableLiveData<SearchMovie> movieResults = new MutableLiveData<>();
        searchApi.getSearchFilm(apiKey, query).enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(Call<SearchMovie> call, Response<SearchMovie> response) {
                if (response.isSuccessful()) {
                    movieResults.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchMovie> call, Throwable t) {
                movieResults.setValue(null);
            }
        });

        return movieResults;
    }

    public MutableLiveData<SearchTv> getSearchTv(String apiKey, String query) {
        final MutableLiveData<SearchTv> tvResults = new MutableLiveData<>();
        searchApi.getSearchTv(apiKey, query).enqueue(new Callback<SearchTv>() {
            @Override
            public void onResponse(Call<SearchTv> call, Response<SearchTv> response) {
                if (response.isSuccessful()) {
                    tvResults.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchTv> call, Throwable t) {
                tvResults.setValue(null);
            }
        });

        return tvResults;
    }
}
