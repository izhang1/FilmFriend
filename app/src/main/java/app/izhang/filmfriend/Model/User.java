package app.izhang.filmfriend.Model;

import java.util.ArrayList;

/**
 * Created by ivanzhang on 2/17/18.
 * User model. Model for the user and other users.
 */

public class User {
    private String username;
    private int id;
    private ArrayList<Integer> savedItems; // Arraylist of Group and Message IDs
}
