package com.example.moviecatalogue2.Model;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SearchTvResults implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("name") private String title;
    @SerializedName("overview") private String description;
    @SerializedName("vote_average") private float rating;
    @SerializedName("poster_path") private String posterImg;
    @SerializedName("backdrop_path") private String bgImg;
    @SerializedName("first_air_date") private String firstAirDate;

    protected SearchTvResults(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        rating = in.readFloat();
        posterImg = in.readString();
        bgImg = in.readString();
        firstAirDate = in.readString();
    }

    public static final Creator<SearchTvResults> CREATOR = new Creator<SearchTvResults>() {
        @Override
        public SearchTvResults createFromParcel(Parcel in) {
            return new SearchTvResults(in);
        }

        @Override
        public SearchTvResults[] newArray(int size) {
            return new SearchTvResults[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeFloat(rating);
        parcel.writeString(posterImg);
        parcel.writeString(bgImg);
        parcel.writeString(firstAirDate);
    }
}
