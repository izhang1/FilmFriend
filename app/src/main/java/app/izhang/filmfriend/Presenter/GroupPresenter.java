package app.izhang.filmfriend.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.UUID;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.FirebaseService;
import app.izhang.filmfriend.Util.LocationUtil;
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
        }else{
            mView.failedUserDialogCreator().show();
        }
    }

    public void addNewGroup(String title, boolean enableLocation){
        String zipCode = "";
        if(enableLocation){
            zipCode = LocationUtil.getZipCode(mView.getContext());
            if(zipCode.isEmpty()){
                LocationUtil.requestLocationPermissions(mView.getActivity());
                LocationUtil.gatherZipCode(mView.getActivity());
            }
            zipCode = LocationUtil.getZipCode(mView.getContext());
        }

        // Setting a new random ID as the group ID
        UUID uuid = UUID.randomUUID();
        String groupID = (enableLocation == true) ? (zipCode + "--"+ uuid.toString()) : uuid.toString();
        Group group = new Group(title, null, mUser.getUid(), groupID);
        firebaseService.createGroup(group);
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
