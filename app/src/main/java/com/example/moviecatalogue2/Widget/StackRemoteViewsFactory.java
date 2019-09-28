package com.example.moviecatalogue2.Widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue2.Database.MyFavoriteDatabase;
import com.example.moviecatalogue2.Database.MyFavoritesDao;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private MyFavoritesDao myFavoritesDao;
    private final Context context;
    private List<MovieResults> list;
    Bitmap preview;

    public StackRemoteViewsFactory(Context context) {
        MyFavoriteDatabase myFavoriteDatabase = MyFavoriteDatabase.getInstance(context);
        this.context = context;
        this.myFavoritesDao = myFavoriteDatabase.myFavoritesDao();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        list.clear();
        list = myFavoritesDao.getMovieWidget();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        try {
            final int fix_position = i + 1;
            preview = Glide.with(context)
                    .asBitmap()
                    .load(list.get(i).getPosterImg())
                    .apply(new RequestOptions().fitCenter())
                    .submit()
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rv.setImageViewBitmap(R.id.imageViewWidget, preview);

        Bundle extras = new Bundle();
        extras.putInt(MovieCatalogueWidget.EXTRA_ITEM, i);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageViewWidget, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
