package com.example.moviecatalogue2.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;

@Database(entities = {TvResults.class, MovieResults.class}, version = 1, exportSchema = false)
public abstract class MyFavoriteDatabase extends RoomDatabase {

    private static MyFavoriteDatabase instance;

    public abstract MyFavoritesDao myFavoritesDao();

    public static synchronized MyFavoriteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyFavoriteDatabase.class, "myFvorites_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
