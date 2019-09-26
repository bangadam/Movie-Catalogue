package com.example.moviecatalogue2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMovie implements Parcelable {
    @SerializedName("results")
    private List<SearchMovieResults> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("page")
    private int page;

    protected SearchMovie(Parcel in) {
        totalResults = in.readInt();
        totalPages = in.readInt();
        page = in.readInt();
    }

    public static final Creator<SearchMovie> CREATOR = new Creator<SearchMovie>() {
        @Override
        public SearchMovie createFromParcel(Parcel in) {
            return new SearchMovie(in);
        }

        @Override
        public SearchMovie[] newArray(int size) {
            return new SearchMovie[size];
        }
    };

    public List<SearchMovieResults> getResults() {
        return results;
    }

    public void setResults(List<SearchMovieResults> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(totalResults);
        parcel.writeInt(totalPages);
        parcel.writeInt(page);
    }
}
