package com.example.moviecatalogue2.View.favorite.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue2.Model.Movie;
import com.example.moviecatalogue2.Model.MovieResults;
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.View.favorite.FavoriteViewModel;
import com.example.moviecatalogue2.View.main.MovieViewModel;
import com.example.moviecatalogue2.View.favorite.RecylerViewMoviesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {


    View view;
    private RecyclerView recyclerView;
    FavoriteViewModel favoriteViewModel;
    @BindView(R.id.progressBar_movies)
    ProgressBar progressBar;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.recyler_movies_favorite);
        progressBar.setVisibility(View.VISIBLE);
        final RecylerViewMoviesAdapter recylerViewMoviesAdapter = new RecylerViewMoviesAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recylerViewMoviesAdapter);

        favoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);
        favoriteViewModel.getAllMovieFavorite().observe(this, new Observer<List<MovieResults>>() {
            @Override
            public void onChanged(@Nullable List<MovieResults> movieResults) {
                recylerViewMoviesAdapter.setFavoriteViewModel(favoriteViewModel);
                recylerViewMoviesAdapter.setMovieModels(movieResults);
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }

}
