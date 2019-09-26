package com.example.moviecatalogue2.View.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.moviecatalogue2.Model.SearchMovie;
import com.example.moviecatalogue2.Model.SearchTv;

import static com.example.moviecatalogue2.Utils.UtilsConstant.API_KEY;

public class SearchViewModel extends AndroidViewModel {
    private SearchRepository searchRepository;
    private MutableLiveData<SearchMovie> searchMovieData;
    private MutableLiveData<SearchTv> searchTvData;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        searchRepository = SearchRepository.getInstance();
    }

    public void searchMovie(String query) {
        searchMovieData = searchRepository.getSearchMovie(API_KEY, query);
    }

    public LiveData<SearchMovie> getMovieResult() {
        return searchMovieData;
    }

    public void searchTv(String query) {
        searchTvData = searchRepository.getSearchTv(API_KEY, query);
    }

    public LiveData<SearchTv> getTvResults() {
        return searchTvData;
    }
}
