package app.izhang.filmfriend.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.Message;
import app.izhang.filmfriend.Presenter.GroupPresenter;
import app.izhang.filmfriend.Presenter.LoginPresenter;
import app.izhang.filmfriend.Presenter.RegisterPresenter;

import static android.content.ContentValues.TAG;

/**
 * Created by ivanzhang on 2/28/18.
 */

public class FirebaseService {

    private final String FB_ACCT = "ACCOUNT";
    private final String FB_GROUP = "GROUP";
    private final String FB_SAVED = "SAVED";

    private final String GROUP_TITLE = "title";
    private final String GROUP_MESSAGE = "message";
    private final String GROUP_ID = "groupId";

    private final String MOVIE_ID = "movieId";

    // Authentication and service
    private static FirebaseService instance = new FirebaseService();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;

    //Get the only object available
    public static FirebaseService getInstance(){
        return instance;
    }

    /**
     * Login method to authenticate with Firebase Auth using an email and password.
     * -
     */
    public void login(String email, String password, final Context context, final LoginPresenter loginPresenter){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    loginPresenter.onResult(true);
                }else{
                    loginPresenter.onResult(false);
                }
            }
        });

    }

    /**
     * Create Account method. Creates a new account with the email and password. Utilizes the UUID returned from Firebase to create a new entry with the username
     */
    public void createAccount(String email, String password, final RegisterPresenter registerPresenter){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            registerPresenter.onResult(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            registerPresenter.onResult(false);

                        }
                    }
                });

    }

    /**
     * Create Group method. Creates a new group entry in the Firebase realtime database system.
     */
    public boolean createGroup(Group group){
        DatabaseReference groupRef = database.getReference(FB_GROUP);
        Task createGroupResult = groupRef.setValue(group);
        if(createGroupResult.isSuccessful()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Search Group method. Returns a list of Groups with the search term.
     */
    public void searchGroup(final String searchTerm, final GroupPresenter groupPresenter){
        DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator groupIter = dataSnapshot.getChildren().iterator();
                ArrayList<Group> groupList = new ArrayList<>();
                while (groupIter.hasNext()){
                    DataSnapshot group = (DataSnapshot) groupIter.next();
                    if(group.child(GROUP_TITLE).getKey().contains(searchTerm)){
                        Group temp = (Group) group.getValue();
                        groupList.add(temp);
                    }
                }
                groupPresenter.searchDataSuccess(groupList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                groupPresenter.searchDataFailure(databaseError.getDetails());
            }
        });
    }

    /**
     * Delete Group method. Deletes the group ID from Firebase.
     */
    public boolean deleteGroup(String groupId){
        DatabaseReference groupRef = database.getReference(FB_GROUP);
        Task result = groupRef.child(groupId).removeValue();
        if(result.isSuccessful()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Add Message method. Adds a new message to the group.
     */
    public void addMessage(final Message newMessage){
        final DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.child(newMessage.getGroupId())
                .child(GROUP_MESSAGE)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    long messageCount = dataSnapshot.getChildrenCount() + 1;
                    groupRef.child(newMessage.getGroupId()).child(Long.toString(messageCount)).setValue(newMessage);
                }else{
                    groupRef.child(newMessage.getGroupId()).child("1").setValue(newMessage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Remove Message method. Removes a message from the group.
     */
    public void removeMessage(Message message){

    }

    /**
     * Edit Message method. Replaces the message in group with new message.
     * @param message
     */
    public void editMessage(Message message){

    }

    /**
     * Save Group method. Saves the group ID into the users account on Firebase.
     * @param groupId
     * @param accountId
     */
    public void saveGroupToAccount(String groupId, String accountId){
        final DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.child(FB_ACCT)
                .child(accountId)
                .child(FB_SAVED)
                .child(groupId)
                .setValue(GROUP_ID);
    }

    /**
     * Save Movie method. Saves the movie ID into the users account on Firebase.
     * @param movieId
     * @param accountId
     */
    public void saveMovieToAccount(String movieId, String accountId){
        final DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.child(FB_ACCT)
                .child(accountId)
                .child(FB_SAVED)
                .child(movieId)
                .setValue(MOVIE_ID);
    }

    /**
     * Get Saved Ids from account method. Returns a list of IDs representing Groups and Movies that were saved.
     * @param accountId
     * todo : Need to consider the interaction as the IDs are being retrieved
     */
    public void getSavedIdsFromAccount(String accountId){
        final DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.child(FB_ACCT)
                .child(accountId)
                .child(FB_SAVED)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String, String> savedIdandType = new HashMap<>();
                        Iterator savedIdIterator = dataSnapshot.getChildren().iterator();

                        while(savedIdIterator.hasNext()){
                            DataSnapshot savedId = (DataSnapshot) savedIdIterator.next();
                            savedIdandType.put(savedId.getKey(), savedId.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    /**
     * Get Group method. Returns Group information based on groupId
     * @param groupId
     */
    public void getGroup(String groupId, final GroupPresenter presenter){
        DatabaseReference groupRef = database.getReference(FB_GROUP);
        groupRef.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = (Group) dataSnapshot.getValue();
                presenter.getGroupResult(group);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                presenter.getGroupResult(null);
            }
        });
    }

}
