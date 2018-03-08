package app.izhang.filmfriend.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.Message;

import static android.content.ContentValues.TAG;

/**
 * Created by ivanzhang on 2/28/18.
 */

public class FirebaseService {

    private final String FB_BASE = "https://filmfriend-17f16.firebaseio.com/";
    private final String FB_ACCT = "https://filmfriend-17f16.firebaseio.com/acct";
    private final String FB_GROUP = "https://filmfriend-17f16.firebaseio.com/group";

    private static FirebaseService instance = new FirebaseService();
    private FirebaseAuth mAuth;

    //Get the only object available
    public static FirebaseService getInstance(){
        return instance;
    }

    /**
     * Login method to authenticate with Firebase Auth using an email and password.
     * -
     */
    private void login(String email, String password, final Context context){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

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
