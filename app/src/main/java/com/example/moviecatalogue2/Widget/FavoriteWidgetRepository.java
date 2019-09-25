package com.example.moviecatalogue2.Widget;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.moviecatalogue2.Database.MyFavoriteDatabase;
import com.example.moviecatalogue2.Database.MyFavoritesDao;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;

import java.util.List;

public class FavoriteWidgetRepository {

    private static FavoriteWidgetRepository favoriteRepository;
    private MyFavoritesDao myFavoritesDao;
    private LiveData<List<MovieResults>> allMoviesFavorite;
    private LiveData<List<TvResults>> allTvFavorite;

    public FavoriteWidgetRepository(Application application) {
        MyFavoriteDatabase database = MyFavoriteDatabase.getInstance(application);
        myFavoritesDao = database.myFavoritesDao();
        allMoviesFavorite = myFavoritesDao.getMovieFavorites();
        allTvFavorite = myFavoritesDao.getTvFavorites();
    }

    public void deleteMovie(MovieResults movieResults) {
        new DeleteMovieAsyncTask(myFavoritesDao).execute(movieResults);
    }

    public LiveData<List<MovieResults>> getAllMoviesFavorite() {
        return allMoviesFavorite;
    }

    public static class DeleteMovieAsyncTask extends AsyncTask<MovieResults, Void, Void> {

        private MyFavoritesDao myFavoritesDao;

        public DeleteMovieAsyncTask(MyFavoritesDao myFavoritesDao) {
            this.myFavoritesDao = myFavoritesDao;
        }

        @Override
        protected Void doInBackground(MovieResults... movieResults) {
            myFavoritesDao.deleteMovie(movieResults[0]);
            return null;
        }
    }

}
