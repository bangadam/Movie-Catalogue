package com.example.moviecatalogue2.View.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.moviecatalogue2.Model.Movie;
import com.example.moviecatalogue2.Model.Tv;


import static com.example.moviecatalogue2.Utils.UtilsConstant.API_KEY;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<Movie> allMovies;
    private MutableLiveData<Tv> allTv;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
        allMovies = movieRepository.getAllMovie(API_KEY);
        allTv = movieRepository.getAllTv(API_KEY);
    }

    public LiveData<Movie> getMovieRepository() {
        return allMovies;
    }

    public LiveData<Tv> getTvRepository() {
        return allTv;
    }
}
