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

import com.example.moviecatalogue2.Model.Tv;
import com.example.moviecatalogue2.Model.TvResults;
import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.View.favorite.FavoriteViewModel;
import com.example.moviecatalogue2.View.main.MovieViewModel;
import com.example.moviecatalogue2.View.favorite.RecylerViewTvShowsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    FavoriteViewModel favoriteViewModel;
    @BindView(R.id.progressBar_Tv)
    ProgressBar progressBar;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tvshows, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.recyler_tv);
        final RecylerViewTvShowsAdapter recylerViewTvShowsAdapter = new RecylerViewTvShowsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recylerViewTvShowsAdapter);

        favoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);
        favoriteViewModel.getAllTvFavorite().observe(this, new Observer<List<TvResults>>() {

            @Override
            public void onChanged(@Nullable List<TvResults> tvResults) {
                recylerViewTvShowsAdapter.setFavoriteViewModel(favoriteViewModel);
                recylerViewTvShowsAdapter.setTvModels(tvResults);
            }
        });
    }

}
