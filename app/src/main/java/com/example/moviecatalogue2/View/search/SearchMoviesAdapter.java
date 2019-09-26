package com.example.moviecatalogue2.View.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.SearchMovieResults;
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.Utils.UtilsConstant;
import com.example.moviecatalogue2.View.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMoviesAdapter extends RecyclerView.Adapter<SearchMoviesAdapter.MyViewHolder> {
    private Context context;

    public List<SearchMovieResults> getSearchMovieModel() {
        return searchMovieModels;
    }

    private List<SearchMovieResults> searchMovieModels;

    public SearchMoviesAdapter(Context context) {
        this.context = context;
        this.searchMovieModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMoviesAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.searchMovieModel = getSearchMovieModel().get(i);
        myViewHolder.tvTitle.setText(searchMovieModels.get(i).getTitle());
        myViewHolder.tvDate.setText(searchMovieModels.get(i).getReleaseDate());
        Glide.with(context).load(UtilsConstant.BASE_POSTER_URL+searchMovieModels.get(i).getPosterImg()).override(150, 200).into(myViewHolder.imgItem);
        myViewHolder.rbScoreMovie.setRating(searchMovieModels.get(i).getRating() / 2 );
    }

    @Override
    public int getItemCount() {
        return searchMovieModels.size();
    }

    public void setSearchMovieModels(List<SearchMovieResults> searchMovieResultList) {
        this.searchMovieModels = searchMovieResultList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_movie) TextView tvTitle;
        @BindView(R.id.date_movie) TextView tvDate;
        @BindView(R.id.rating_movie_item) RatingBar rbScoreMovie;
        @BindView(R.id.img_item_movie) ImageView imgItem;
        @BindView(R.id.btn_detail_movie) Button btnDetail;

        SearchMovieResults searchMovieModel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDetail.setVisibility(View.GONE);
        }
    }
}
