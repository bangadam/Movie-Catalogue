package com.example.moviecatalogue2.View.main.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.moviecatalogue2.View.main.MovieViewModel;
import com.example.moviecatalogue2.View.main.RecylerViewTvShowsAdapter;
import com.example.moviecatalogue2.View.search.SearchActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    MovieViewModel movieViewModel;
    @BindView(R.id.progressBar_Tv) ProgressBar progressBar;
    @BindView(R.id.search_tv)
    SearchBox searchBox;
    public TVShowsFragment() {
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

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel.getTvRepository().observe(this, new Observer<Tv>() {
            @Override
            public void onChanged(@Nullable Tv tv) {
                List<TvResults> tvResults = tv.getResults();
                recylerViewTvShowsAdapter.setTvModels(tvResults);
            }
        });

        searchBox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {

            }

            @Override
            public void onSearchTermChanged(String s) {

            }

            @Override
            public void onSearch(String s) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("key", s);
                intent.putExtra("fragment", "tv");
                startActivity(intent);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

            }
        });
    }
}
