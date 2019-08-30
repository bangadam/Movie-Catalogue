package com.example.moviecatalogue2.View.main.fragment;

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
import com.example.moviecatalogue2.View.main.MovieViewModel;
import com.example.moviecatalogue2.View.main.RecylerViewMoviesAdapter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    MovieViewModel movieViewModel;
    @BindView(R.id.progressBar_movies) ProgressBar progressBar;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.recyler_movies);
        progressBar.setVisibility(View.VISIBLE);
        final RecylerViewMoviesAdapter recylerViewMoviesAdapter = new RecylerViewMoviesAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recylerViewMoviesAdapter);

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel.getMovieRepository().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                List<MovieResults> movieResults = movie.getResults();
                recylerViewMoviesAdapter.setMovieModels(movieResults);
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }
}
