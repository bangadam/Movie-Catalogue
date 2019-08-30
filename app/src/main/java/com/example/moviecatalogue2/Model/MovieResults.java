package com.example.moviecatalogue2.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class MovieResults implements Parcelable {
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

    public MovieResults(int id, String title, String posterImg, String backgroundImg, float rating, String description, String releaseDate) {
        this.id = id;
        this.title = title;
        this.posterImg = posterImg;
        this.backgroundImg = backgroundImg;
        this.rating = rating;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static Creator<MovieResults> getCREATOR() {
        return CREATOR;
    }

    protected MovieResults(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterImg = in.readString();
        backgroundImg = in.readString();
        rating = in.readFloat();
        description = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(posterImg);
        dest.writeString(backgroundImg);
        dest.writeFloat(rating);
        dest.writeString(description);
        dest.writeString(releaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
        @Override
        public MovieResults createFromParcel(Parcel in) {
            return new MovieResults(in);
        }

        @Override
        public MovieResults[] newArray(int size) {
            return new MovieResults[size];
        }
    };
}
