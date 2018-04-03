package app.izhang.filmfriend.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by ivanzhang on 2/17/18.
 * - Group model that contains information about a specific group of users who are messaging each other.
 */
@IgnoreExtraProperties
public class Group implements Parcelable{
    private String title; // Title of this group
    private ArrayList<Message> messages; // Messages that is current part of this group
    private String owner; // User id of this group
    private String id; // ID of this group
    private String zipcode;

    // Default constructor for calls to DataSnapshot.getValue
    public Group(){}

    public Group(String title, ArrayList<Message> messages, String owner, String id) {
        this.title = title;
        this.messages = messages;
        this.owner = owner;
        this.id = id;
        this.zipcode = "";
    }


    // Parcelable Implementation
    private Group(Parcel in){
        title = in.readString();
        messages = in.readArrayList(Message.class.getClassLoader());
        owner = in.readString();
        id = in.readString();
        zipcode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeList(messages);
        dest.writeString(owner);
        dest.writeString(id);
        dest.writeString(zipcode);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        public Group[] newArray(int size) {
            return new Group[size];
        }
    };


    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZipcode(){
        return zipcode;
    }

    public void setZipcode(String zipCode){
        this.zipcode = zipCode;
    }
}
