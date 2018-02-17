package app.izhang.filmfriend.Model;

import java.util.ArrayList;

/**
 * Created by ivanzhang on 2/17/18.
 * - Group model that contains information about a specific group of users who are messaging each other.
 */

public class Group {
    private String title; // Title of this group
    private ArrayList<Message> messages; // Messages that is current part of this group
    private int owner; // User id of this group
    private int id; // ID of this group
}
