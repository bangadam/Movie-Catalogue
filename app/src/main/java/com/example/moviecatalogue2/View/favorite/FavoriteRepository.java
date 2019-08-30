package com.example.moviecatalogue2.View.favorite;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.moviecatalogue2.Database.MyFavoriteDatabase;
import com.example.moviecatalogue2.Database.MyFavoritesDao;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;

import java.util.List;

public class FavoriteRepository {

    private static FavoriteRepository favoriteRepository;
    private MyFavoritesDao myFavoritesDao;
    private LiveData<List<MovieResults>> allMoviesFavorite;
    private LiveData<List<TvResults>> allTvFavorite;

    public FavoriteRepository(Application application) {
        MyFavoriteDatabase database = MyFavoriteDatabase.getInstance(application);
        myFavoritesDao = database.myFavoritesDao();
        allMoviesFavorite = myFavoritesDao.getMovieFavorites();
        allTvFavorite = myFavoritesDao.getTvFavorites();
    }

    public void insertMovie(MovieResults movieResults) {
        new InsertMovieAsyncTask(myFavoritesDao).execute(movieResults);
    }

    public void insertTv(TvResults tvResults) {
        new InsertTvAsyncTask(myFavoritesDao).execute(tvResults);
    }

    public void deleteMovie(MovieResults movieResults) {
        new DeleteMovieAsyncTask(myFavoritesDao).execute(movieResults);
    }

    public void deleteTv(TvResults tvResults) {
        new DeleteTvAsyncTask(myFavoritesDao).execute(tvResults);
    }

    public LiveData<List<MovieResults>> getAllMoviesFavorite() {
        return allMoviesFavorite;
    }

    public LiveData<List<TvResults>> getAllTvFavorite() {
        return allTvFavorite;
    }

    public static class InsertMovieAsyncTask extends AsyncTask<MovieResults, Void, Void> {

        private MyFavoritesDao myFavoritesDao;

        public InsertMovieAsyncTask(MyFavoritesDao myFavoritesDao) {
            this.myFavoritesDao = myFavoritesDao;
        }

        @Override
        protected Void doInBackground(MovieResults... movieResults) {
            myFavoritesDao.insertMovie(movieResults[0]);
            return null;
        }
    }

    public static class InsertTvAsyncTask extends AsyncTask<TvResults, Void, Void> {

        private MyFavoritesDao myFavoritesDao;

        public InsertTvAsyncTask(MyFavoritesDao myFavoritesDao) {
            this.myFavoritesDao = myFavoritesDao;
        }

        @Override
        protected Void doInBackground(TvResults... tvResults) {
            myFavoritesDao.insertTv(tvResults[0]);
            return null;
        }
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

    public static class DeleteTvAsyncTask extends AsyncTask<TvResults, Void, Void> {

        private MyFavoritesDao myFavoritesDao;

        public DeleteTvAsyncTask(MyFavoritesDao myFavoritesDao) {
            this.myFavoritesDao = myFavoritesDao;
        }

        @Override
        protected Void doInBackground(TvResults... tvResults) {
            myFavoritesDao.deleteTv(tvResults[0]);
            return null;
        }
    }
}
