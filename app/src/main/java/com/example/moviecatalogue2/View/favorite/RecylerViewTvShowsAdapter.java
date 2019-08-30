package com.example.moviecatalogue2.View.favorite;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.Model.TvResults;
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.Utils.UtilsConstant;
import com.example.moviecatalogue2.View.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecylerViewTvShowsAdapter extends RecyclerView.Adapter<RecylerViewTvShowsAdapter.MyViewHolder> {
    private Context context;

    public List<TvResults> getTvModels() {
        return tvModels;
    }

    private List<TvResults> tvModels;

    private FavoriteViewModel favoriteViewModel;

    public RecylerViewTvShowsAdapter(Context context) {
        this.context = context;
        this.tvModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecylerViewTvShowsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_show_item_favorite, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewTvShowsAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tvModel = getTvModels().get(i);
        myViewHolder.tvTitle.setText(tvModels.get(i).getTitle());
        myViewHolder.tvDate.setText(tvModels.get(i).getFirstAirDate());
        Glide.with(context).load(UtilsConstant.BASE_POSTER_URL+tvModels.get(i).getPosterImg()).override(150, 200).into(myViewHolder.imgItem);
        myViewHolder.rbScoreMovie.setRating(tvModels.get(i).getRating()/2);
    }

    @Override
    public int getItemCount() {
        return tvModels.size();
    }

    public TvResults getTvAt(int position) {
        return tvModels.get(position);
    }

    public void setTvModels(List<TvResults> tvResultsList) {
        this.tvModels = tvResultsList;
        notifyDataSetChanged();
    }

    public void setFavoriteViewModel(FavoriteViewModel favoriteViewModel) {
        this.favoriteViewModel = favoriteViewModel;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_movie) TextView tvTitle;
        @BindView(R.id.date_movie) TextView tvDate;
        @BindView(R.id.rating_movie_item) RatingBar rbScoreMovie;
        @BindView(R.id.img_item_movie) ImageView imgItem;
        @BindView(R.id.btn_delete) Button btnDelete;

        TvResults tvModel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgItem.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           switch (v.getId()) {
               case R.id.img_item_movie :
                   Intent intent = new Intent(context, DetailMovieActivity.class);
                   intent.putExtra("key", tvModel);
                   intent.putExtra("fragment", "tv");
                   context.startActivity(intent);
                   break;
               case R.id.btn_delete :
                   favoriteViewModel.deleteTv(getTvAt(getAdapterPosition()));
                   Toast.makeText(v.getContext(), R.string.delete_favorite_message, Toast.LENGTH_SHORT).show();
                   break;
           }
        }
    }
}
