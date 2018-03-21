package app.izhang.filmfriend.Presenter;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Util.FirebaseService;
import app.izhang.filmfriend.View.Base.BaseDataView;
import app.izhang.filmfriend.View.GroupFragment;

/**
 * Created by ivanzhang on 3/10/18.
 */

public class GroupPresenter {

    private GroupFragment mView;
    private FirebaseService firebaseService = FirebaseService.getInstance();
    private FirebaseUser mUser;

    public GroupPresenter(GroupFragment view){
        this.mView = view;
    }

    public void checkIfLoggedIn(){
        if(firebaseService.userIsSignedIn()){
            mView.groupDialogCreator().show();
            mUser = firebaseService.getUserInfo();
        }else{
            mView.failedUserDialogCreator().show();
        }
    }

    public void addNewGroup(String title, boolean enableLocation){
        Group group = new Group(title, null, mUser.getDisplayName(), mUser.getUid());
        firebaseService.createGroup(group, enableLocation);
    }


    public void deleteGroup(){

    }

    public void deleteGroupResults(){

    }

    public void getGroupId(String id){

    }

    public void getGroupResult(Group group){

    }

}
