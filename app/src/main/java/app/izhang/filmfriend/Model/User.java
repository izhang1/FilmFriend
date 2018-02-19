package app.izhang.filmfriend.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ivanzhang on 2/17/18.
 * User model. Model for the user and other users.
 */

public class User implements Parcelable{
    private String username;
    private String id;
    private ArrayList<Integer> savedItems; // Arraylist of Group and Message IDs

    public User(String username, String id, ArrayList<Integer> savedItems){
        this.username = username;
        this.id = id;
        this.savedItems = savedItems;
    }

    // Parcelable implementation

    private User(Parcel in){
        username = in.readString();
        id = in.readString();
        savedItems = in.readArrayList(User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(username);
        dest.writeString(id);
        dest.writeList(savedItems);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // Getters and Settings

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Integer> getSavedItems() {
        return savedItems;
    }

    public void setSavedItems(ArrayList<Integer> savedItems) {
        this.savedItems = savedItems;
    }
}
