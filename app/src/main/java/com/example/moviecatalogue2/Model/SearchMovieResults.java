package com.example.moviecatalogue2.Model;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SearchMovieResults implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @SerializedName("poster_path")
    private String posterImg;
    @SerializedName("backdrop_path")
    private String backgroundImg;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("overview")
    private String description;
    @SerializedName("release_date")
    private String releaseDate;


    protected SearchMovieResults(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterImg = in.readString();
        backgroundImg = in.readString();
        rating = in.readFloat();
        description = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<SearchMovieResults> CREATOR = new Creator<SearchMovieResults>() {
        @Override
        public SearchMovieResults createFromParcel(Parcel in) {
            return new SearchMovieResults(in);
        }

        @Override
        public SearchMovieResults[] newArray(int size) {
            return new SearchMovieResults[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(posterImg);
        parcel.writeString(backgroundImg);
        parcel.writeFloat(rating);
        parcel.writeString(description);
        parcel.writeString(releaseDate);
    }
}
