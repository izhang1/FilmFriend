package app.izhang.filmfriend.Util;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.Message;

/**
 * Created by ivanzhang on 2/28/18.
 */

public class FirebaseService {

    private final String FB_BASE = "https://filmfriend-17f16.firebaseio.com/";
    private final String FB_ACCT = "https://filmfriend-17f16.firebaseio.com/acct";
    private final String FB_GROUP = "https://filmfriend-17f16.firebaseio.com/group";


    /**
     * Login method to authenticate with Firebase Auth using an email and password.
     */
    private void login(String email, String password){}

    /**
     * Create Account method. Creates a new account with the email and password. Utilizes the UUID returned from Firebase to create a new entry with the username
     */
    private void createAccount(String email, String username, String password){}

    /**
     * Create Group method. Creates a new group entry in the Firebase realtime database system.
     */
    private void createGroup(Group group){}

    /**
     * Search Group method. Returns a list of Groups with the search term.
     */
    private void searchGroup(String searchTerm){}

    /**
     * Delete Group method. Deletes the group ID from Firebase.
     */
    private void deleteGroup(String groupId){}

    /**
     * Add Message method. Adds a new method to the group.
     */
    private void addMessage(Message newMessage){}

    /**
     * Remove Message method. Removes a message from the group.
     */
    private void removeMessage(Message message){}

    /**
     * Edit Message method. Replaces the message in group with new message.
     * @param message
     */
    private void editMessage(Message message){}

    /**
     * Save Group method. Saves the group ID into the users account on Firebase.
     * @param groupId
     * @param accountId
     */
    private void saveGroupToAccount(String groupId, String accountId){}

    /**
     * Save Movie method. Saves the movie ID into the users account on Firebase.
     * @param movieId
     * @param accountId
     */
    private void saveMovieToAccount(String movieId, String accountId){}

    /**
     * Get Saved Ids from account method. Returns a list of IDs representing Groups and Movies that were saved.
     * @param accountId
     */
    private void getSavedIdsFromAccount(String accountId){}

    /**
     * Get Group method. Returns Group information based on groupId
     * @param groupId
     */
    private void getGroup(String groupId){}

}
