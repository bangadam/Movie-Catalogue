package com.example.moviecatalogue2.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tv_table")
public class TvResults implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("name") private String title;
    @SerializedName("overview") private String description;
    @SerializedName("vote_average") private float rating;
    @SerializedName("poster_path") private String posterImg;
    @SerializedName("backdrop_path") private String bgImg;
    @SerializedName("first_air_date") private String firstAirDate;

    public TvResults(int id, String title, String description, float rating, String posterImg, String bgImg, String firstAirDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.posterImg = posterImg;
        this.bgImg = bgImg;
        this.firstAirDate = firstAirDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    protected TvResults(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        rating = in.readFloat();
        posterImg = in.readString();
        bgImg = in.readString();
        firstAirDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeFloat(rating);
        dest.writeString(posterImg);
        dest.writeString(bgImg);
        dest.writeString(firstAirDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvResults> CREATOR = new Creator<TvResults>() {
        @Override
        public TvResults createFromParcel(Parcel in) {
            return new TvResults(in);
        }

        @Override
        public TvResults[] newArray(int size) {
            return new TvResults[size];
        }
    };
}
