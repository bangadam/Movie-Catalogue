package com.example.moviecatalogue2.View.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.moviecatalogue2.View.favorite.FavoriteViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title_movie) TextView tvTitle;
    @BindView(R.id.tgl_detail_movie) TextView tvDate;
    @BindView(R.id.description_movie) TextView tvDescription;
    @BindView(R.id.img_detail_movie) ImageView imgPoster;
    @BindView(R.id.bg_img_detail_movie) ImageView imgBg;
    @BindView(R.id.score_detail_movie) RatingBar rbScoreMovie;
    @BindView(R.id.btn_favorite) Button btnFavorite;

    private FavoriteViewModel favoriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        if (getIntent().getStringExtra("fragment").equals("movie")) {
            MovieResults movieModel = getIntent().getParcelableExtra("key");
            tvTitle.setText(movieModel.getTitle());
            tvDate.setText(movieModel.getReleaseDate());
            tvDescription.setText(movieModel.getDescription());
            rbScoreMovie.setRating(movieModel.getRating() / 2);

            Glide.with(this).load(UtilsConstant.BASE_BACKDROP_URL + movieModel.getBackgroundImg()).into(imgBg);
            Glide.with(this).load(UtilsConstant.BASE_POSTER_URL + movieModel.getPosterImg()).into(imgPoster);

        } else if (getIntent().getStringExtra("fragment").equals("tv")) {
            TvResults tvResults = getIntent().getParcelableExtra("key");
            tvTitle.setText(tvResults.getTitle());
            tvDate.setText(tvResults.getFirstAirDate());
            tvDescription.setText(tvResults.getDescription());
            rbScoreMovie.setRating(tvResults.getRating() / 2);

            Glide.with(this).load(UtilsConstant.BASE_BACKDROP_URL + tvResults.getBgImg()).into(imgBg);
            Glide.with(this).load(UtilsConstant.BASE_POSTER_URL + tvResults.getPosterImg()).into(imgPoster);
        }

            btnFavorite.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch (getIntent().getStringExtra("fragment")) {
            case "movie" :
                MovieResults movieResults = getIntent().getParcelableExtra("key");
                favoriteViewModel.insertMovie(movieResults);
                Toast.makeText(this, R.string.add_favorite_message, Toast.LENGTH_SHORT).show();
                break;
            case "tv" :
                TvResults tvResults = getIntent().getParcelableExtra("key");
                favoriteViewModel.insertTv(tvResults);
                Toast.makeText(this, R.string.add_favorite_message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
