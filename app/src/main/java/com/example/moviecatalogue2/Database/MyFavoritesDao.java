package com.example.moviecatalogue2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;

import java.util.List;

@Dao
public interface MyFavoritesDao {

    @Insert
    void insertMovie(MovieResults movieResults);

    @Insert
    void insertTv(TvResults tvResults);

    @Delete
    void deleteMovie(MovieResults movieResults);

    @Delete
    void deleteTv(TvResults tvResults);

    @Query("SELECT * FROM movie_table")
    LiveData<List<MovieResults>> getMovieFavorites();

    @Query("SELECT * FROM movie_table")
    List<MovieResults> getMovieWidget();

    @Query("SELECT * FROM tv_table")
    LiveData<List<TvResults>> getTvFavorites();
}
