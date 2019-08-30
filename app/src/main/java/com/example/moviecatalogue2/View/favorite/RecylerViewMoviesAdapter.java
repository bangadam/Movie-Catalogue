package com.example.moviecatalogue2.View.favorite;

import android.arch.lifecycle.ViewModelProviders;
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
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.Utils.UtilsConstant;
import com.example.moviecatalogue2.View.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecylerViewMoviesAdapter extends RecyclerView.Adapter<RecylerViewMoviesAdapter.MyViewHolder> {
    private Context context;

    public List<MovieResults> getMovieModels() {
        return movieModels;
    }

    private List<MovieResults> movieModels;

    private FavoriteViewModel favoriteViewModel;

    public RecylerViewMoviesAdapter(Context context) {
        this.context = context;
        this.movieModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecylerViewMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_item_favorite, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewMoviesAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.movieModel = getMovieModels().get(i);
        myViewHolder.tvTitle.setText(movieModels.get(i).getTitle());
        myViewHolder.tvDate.setText(movieModels.get(i).getReleaseDate());
        Glide.with(context).load(UtilsConstant.BASE_POSTER_URL+movieModels.get(i).getPosterImg()).override(150, 200).into(myViewHolder.imgItem);
        myViewHolder.rbScoreMovie.setRating(movieModels.get(i).getRating() / 2 );
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public void setMovieModels(List<MovieResults> movieResultsList) {
        this.movieModels = movieResultsList;
        notifyDataSetChanged();
    }

    public void setFavoriteViewModel(FavoriteViewModel favoriteViewModel) {
        this.favoriteViewModel = favoriteViewModel;
        notifyDataSetChanged();
    }

    public MovieResults getMovieAt(int position) {
        return movieModels.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_movie) TextView tvTitle;
        @BindView(R.id.date_movie) TextView tvDate;
        @BindView(R.id.rating_movie_item) RatingBar rbScoreMovie;
        @BindView(R.id.img_item_movie) ImageView imgItem;
        @BindView(R.id.btn_delete) Button btnDelete;

        MovieResults movieModel;

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
                    intent.putExtra("key", movieModel);
                    intent.putExtra("fragment", "movie");
                    context.startActivity(intent);
                    break;
                case R.id.btn_delete :
                    favoriteViewModel.deleteMovie(getMovieAt(getAdapterPosition()));
                    Toast.makeText(v.getContext(), R.string.delete_favorite_message, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
