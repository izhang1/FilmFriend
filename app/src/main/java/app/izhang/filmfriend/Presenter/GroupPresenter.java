package app.izhang.filmfriend.Presenter;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.Services.ZipCodeService;
import app.izhang.filmfriend.Model.Services.ZipJsonResponse;
import app.izhang.filmfriend.Model.SharedPreferenceManager;
import app.izhang.filmfriend.Model.Services.FirebaseService;
import app.izhang.filmfriend.Util.LocationUtil;
import app.izhang.filmfriend.Util.NetworkUtil;
import app.izhang.filmfriend.View.GroupFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivanzhang on 3/10/18.
 */

public class GroupPresenter {

    private GroupFragment mView;
    private FirebaseService firebaseService = FirebaseService.getInstance();
    private FirebaseUser mUser;
    private String DEFAULT_RADIUS = "10"; // miles

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

            // Save group to user account since they created it (assume user  will favorite it)
            firebaseService.saveGroupToAccount(group.getId(), mUser.getUid());

            // Callback to the view
            mView.showAddedGroup(group);
        }else{

        }

    }

    public void getGroupsLoc(){
        mView.showLoadingState(true);
        getZipCodeFromService();
    }

    public void getGroupsFromZipCode(ArrayList<String> zipCodes){
        FirebaseService fbService = FirebaseService.getInstance();
        fbService.getGroupsWithZip(zipCodes, this);
    }

    public void getZipCodeFromService(){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(mView.getContext());
        final String zipCode = sharedPreferenceManager.getZipCodeFromPref();
        Retrofit retrofit = NetworkUtil.getRetrofitLocInstance();

        ZipCodeService service = retrofit.create(ZipCodeService.class);
        Call<ZipJsonResponse> zipCodeCall = service.getZipCodesByRadius(zipCode, DEFAULT_RADIUS);
        zipCodeCall.enqueue(new Callback<ZipJsonResponse>() {
            @Override
            public void onResponse(Call<ZipJsonResponse> call, Response<ZipJsonResponse> response) {
                if(response.isSuccessful()){
                    ZipJsonResponse zipResponse = response.body();
                    ArrayList<String> zipCodes = new ArrayList<>(Arrays.asList(zipResponse.getResults()));
                    getGroupsFromZipCode(zipCodes);
                }else{
                    Log.v("Call: ", call.toString());
                    Log.v("Not Successful: ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ZipJsonResponse> call, Throwable t) {
                Log.v("Call: ", call.toString());
                Log.v("Not Successful: ", "Call Failed \n");
                t.printStackTrace();
            }
        });
    }

    public void getGroupsLocResults(ArrayList groups){
        mView.getLocDataSuccess(groups);
        mView.showLoadingState(false);
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
