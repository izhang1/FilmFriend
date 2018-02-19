package app.izhang.filmfriend.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivanzhang on 2/17/18.
 * - Movie model. Contains information about one movie.
 * - Model taken from TopRatedMovies application
 */

public class Movie implements Parcelable {
    private String title;
    private String release_date;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private double vote_average;
    private String id;

    public Movie(String title, String release_date, String posterPath,String backdrop_path, String overview, double voteAverage, String id){
        this.title = title;
        this.release_date = release_date;
        this.poster_path = posterPath;
        this.overview = overview;
        this.vote_average = voteAverage;
        this.id = id;
        this.backdrop_path = backdrop_path;
    }

    private Movie(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readInt();
        id = in.readString();
    }

    // Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeDouble(vote_average);
        dest.writeString(id);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public String toString() {
        return "Movie: Title: " + title + "\n"
                + "Release Date: " + release_date + "\n"
                + "Poster Path: " + poster_path + "\n"
                + "Backdrop Path: " + backdrop_path + "\n"
                + "Overview: " + overview + "\n"
                + "Movie id: " + id + "\n"
                + "Vote Average: " + vote_average;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}