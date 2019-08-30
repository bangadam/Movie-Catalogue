package com.example.moviecatalogue2.View.favorite;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteRepository repository;
    private LiveData<List<MovieResults>> allMovieFavorite;
    private LiveData<List<TvResults>> allTvFavorite;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allMovieFavorite = repository.getAllMoviesFavorite();
        allTvFavorite = repository.getAllTvFavorite();
    }

    public void insertMovie(MovieResults movieResults) {
        repository.insertMovie(movieResults);
    }

    public void insertTv(TvResults tvResults) {
        repository.insertTv(tvResults);
    }

    public void deleteMovie(MovieResults movieResults) {
        repository.deleteMovie(movieResults);
    }

    public void deleteTv(TvResults tvResults) {
        repository.deleteTv(tvResults);
    }

    public LiveData<List<MovieResults>> getAllMovieFavorite() {
        return allMovieFavorite;
    }

    public LiveData<List<TvResults>> getAllTvFavorite() {
        return allTvFavorite;
    }

}
