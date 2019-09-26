package com.example.moviecatalogue2.View.search;

import android.content.Context;
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
import com.example.moviecatalogue2.Model.SearchTvResults;
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.Utils.UtilsConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvAdapter extends RecyclerView.Adapter<SearchTvAdapter.MyViewHolder> {
    private Context context;

    public List<SearchTvResults> getSearchTvModels() {
        return searchTvModels;
    }

    private List<SearchTvResults> searchTvModels;

    public SearchTvAdapter(Context context) {
        this.context = context;
        this.searchTvModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchTvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_show_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTvAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.searchTvResults = getSearchTvModels().get(i);
        myViewHolder.tvTitle.setText(searchTvModels.get(i).getTitle());
        myViewHolder.tvDate.setText(searchTvModels.get(i).getFirstAirDate());
        Glide.with(context).load(UtilsConstant.BASE_POSTER_URL+searchTvModels.get(i).getPosterImg()).override(150, 200).into(myViewHolder.imgItem);
        myViewHolder.rbScoreMovie.setRating(searchTvModels.get(i).getRating()/2);
    }

    @Override
    public int getItemCount() {
        return searchTvModels.size();
    }

    public void setSearchTvModels(List<SearchTvResults> searchTvModels) {
        this.searchTvModels = searchTvModels;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_movie) TextView tvTitle;
        @BindView(R.id.date_movie) TextView tvDate;
        @BindView(R.id.rating_movie_item) RatingBar rbScoreMovie;
        @BindView(R.id.img_item_movie) ImageView imgItem;
        @BindView(R.id.btn_detail_movie) Button btnDetail;

        SearchTvResults searchTvResults;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDetail.setVisibility(View.GONE);
        }
    }
}
