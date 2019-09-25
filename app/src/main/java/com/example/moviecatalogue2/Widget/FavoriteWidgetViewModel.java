package com.example.moviecatalogue2.Widget;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;
import com.example.moviecatalogue2.View.favorite.FavoriteRepository;

import java.util.List;

public class FavoriteWidgetViewModel extends AndroidViewModel {

    private FavoriteRepository repository;
    private LiveData<List<MovieResults>> allMovieFavorite;

    public FavoriteWidgetViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allMovieFavorite = repository.getAllMoviesFavorite();
    }

    public void deleteMovie(MovieResults movieResults) {
        repository.deleteMovie(movieResults);
    }

    public LiveData<List<MovieResults>> getAllMovieFavorite() {
        return allMovieFavorite;
    }

}
