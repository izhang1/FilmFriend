package app.izhang.filmfriend.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ivanzhang on 2/17/18.
 * - Message model. Contains information about a message and the owner.
 */

public class Message implements Parcelable{
    private String message; // message contents
    private String owner; // ID of the message owner
    private String ownerUsername; // Username of the message owner
    private String date; // date when the message was posted
    private String groupId; // ID of the group that the message is in

    public Message(String message, String owner, String ownerUsername, String date, String groupId) {
        this.message = message;
        this.owner = owner;
        this.ownerUsername = ownerUsername;
        this.date = date;
        this.groupId = groupId;
    }

    // Parcelable Implementation
    private Message(Parcel in){
        message = in.readString();
        owner = in.readString();
        ownerUsername = in.readString();
        date = in.readString();
        groupId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(message);
        dest.writeString(owner);
        dest.writeString(ownerUsername);
        dest.writeString(date);
        dest.writeString(groupId);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
