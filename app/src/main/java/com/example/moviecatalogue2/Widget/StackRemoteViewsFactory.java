//package com.example.moviecatalogue2.Widget;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.widget.RemoteViews;
//import android.widget.RemoteViewsService;
//
//import com.example.moviecatalogue2.Model.MovieResults;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
//
//    private FavoriteWidgetViewModel favoriteWidgetViewModel;
//    private final Context context;
//    private List<MovieResults> list;
//    Bitmap preview;
//
//    public StackRemoteViewsFactory(Context context) {
//        this.context = context;
//        this.list = new ArrayList<>();
//    }
//
//    @Override
//    public void onCreate() {
//
//    }
//
//    @Override
//    public void onDataSetChanged() {
//        list.clear();
//        list = favoriteWidgetViewModel.getAllMovieFavorite();
//    }
//
//    @Override
//    public void onDestroy() {
//
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public RemoteViews getViewAt(int i) {
//
//    }
//
//    @Override
//    public RemoteViews getLoadingView() {
//        return null;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 0;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//}
