package com.example.moviecatalogue2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTv implements Parcelable {
    @SerializedName("results")
    private List<SearchTvResults> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("page")
    private int page;

    protected SearchTv(Parcel in) {
        totalResults = in.readInt();
        totalPages = in.readInt();
        page = in.readInt();
    }

    public static final Creator<SearchTv> CREATOR = new Creator<SearchTv>() {
        @Override
        public SearchTv createFromParcel(Parcel in) {
            return new SearchTv(in);
        }

        @Override
        public SearchTv[] newArray(int size) {
            return new SearchTv[size];
        }
    };

    public List<SearchTvResults> getResults() {
        return results;
    }

    public void setResults(List<SearchTvResults> results) {
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
