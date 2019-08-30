package com.example.moviecatalogue2.View.main;

import android.arch.lifecycle.MutableLiveData;

import com.example.moviecatalogue2.Model.Movie;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.Tv;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository movieRepository;
    private MovieApi movieApi;

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MovieRepository() {
        movieApi = MovieClient.createService(MovieApi.class);
    }

    public MutableLiveData<Movie> getAllMovie(String apiKey) {
       final MutableLiveData<Movie> moviesData = new MutableLiveData<>();
       movieApi.getMoviePopular(apiKey).enqueue(new Callback<Movie>() {
           @Override
           public void onResponse(Call<Movie> call, Response<Movie> response) {
               if (response.isSuccessful()) {
                   moviesData.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<Movie> call, Throwable t) {
                moviesData.setValue(null);
           }
       });

       return moviesData;
    }

    public MutableLiveData<Tv> getAllTv(String apiKey) {
        final MutableLiveData<Tv> tvData = new MutableLiveData<>();
        movieApi.getTvPopular(apiKey).enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                if (response.isSuccessful()) {
                    tvData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {
                tvData.setValue(null);
            }
        });

        return tvData;
    }

}
