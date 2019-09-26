package com.example.moviecatalogue2.View.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.moviecatalogue2.Model.SearchMovie;
import com.example.moviecatalogue2.Model.SearchMovieResults;
import com.example.moviecatalogue2.Model.SearchTv;
import com.example.moviecatalogue2.Model.SearchTvResults;
import com.example.moviecatalogue2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.loading_search_movie) ProgressBar progressBar;
    @BindView(R.id.recyler_movies) RecyclerView recyclerView;
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        String query = getIntent().getStringExtra("key");
        String fragment = getIntent().getStringExtra("fragment");

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        if (fragment.equals("movie")) {
            final SearchMoviesAdapter searchMoviesAdapter = new SearchMoviesAdapter(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(searchMoviesAdapter);

            searchViewModel.searchMovie(query);
            searchViewModel.getMovieResult().observe(this, new Observer<SearchMovie>() {
                @Override
                public void onChanged(@Nullable SearchMovie searchMovie) {
                    List<SearchMovieResults> searchMovieResultsList = searchMovie.getResults();
                    searchMoviesAdapter.setSearchMovieModels(searchMovieResultsList);
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else if (fragment.equals("tv")) {
            final SearchTvAdapter searchTvAdapter = new SearchTvAdapter(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(searchTvAdapter);

            searchViewModel.searchTv(query);
            searchViewModel.getTvResults().observe(this, new Observer<SearchTv>() {
                @Override
                public void onChanged(@Nullable SearchTv searchTv) {
                    List<SearchTvResults> searchTvResults = searchTv.getResults();
                    searchTvAdapter.setSearchTvModels(searchTvResults);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}
