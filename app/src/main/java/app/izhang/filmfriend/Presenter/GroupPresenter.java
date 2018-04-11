package app.izhang.filmfriend.Presenter;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.UUID;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.SharedPreferenceManager;
import app.izhang.filmfriend.Model.Services.FirebaseService;
import app.izhang.filmfriend.Util.LocationUtil;
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

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(mView.getContext());
        if(enableLocation){
            zipCode = sharedPreferenceManager.getZipCodeFromPref();
            if(zipCode.isEmpty()){
                LocationUtil.requestLocationPermissions(mView.getActivity());
                LocationUtil.gatherZipCode(mView.getActivity());
            }
        }

        // Setting a new random ID as the group ID
        if(FirebaseService.getInstance().userIsSignedIn()){
            mUser = FirebaseService.getInstance().getUserInfo();
            UUID uuid = UUID.randomUUID();
            Group group = new Group(title, null, mUser.getUid(), uuid.toString());
            group.setZipcode(zipCode);
            Log.v("AddNewGroup", "calling Firebase Service to add group");
            firebaseService.createGroup(group);

            // Callback to the view
            mView.showAddedGroup(group);
        }else{

        }

    }

    // Each pageNum = 40 groups
    public void getGroups(int pageNum){
        mView.showLoadingState(true);
        firebaseService.userIsSignedIn();
        firebaseService.getGroups(pageNum, this);
    }

    public void getGroupsResults(ArrayList groups){
        mView.getDataSuccess(groups);
        mView.showLoadingState(false);
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
