package app.izhang.filmfriend.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.Model.SharedPreferenceManager;
import app.izhang.filmfriend.Presenter.GroupPresenter;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.EndlessScrollListener;
import app.izhang.filmfriend.View.Adapter.MyGroupLocRecyclerViewAdapter;
import app.izhang.filmfriend.View.Adapter.MyGroupRecyclerViewAdapter;
import app.izhang.filmfriend.View.Base.BaseDataView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class GroupFragment extends Fragment implements BaseDataView {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private EndlessScrollListener scrollListener;


    private int pageNum = 1;

    @BindView(R.id.group_rv) RecyclerView mGroupRV;
    @BindView(R.id.fab) FloatingActionButton addGroupFAB;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    private MenuItem mLocToggleMenu;
    private boolean locationIsEnabled = false;

    private LinearLayoutManager mGroupLayoutManager;
    private GroupPresenter mGroupPresenter;
    private MyGroupRecyclerViewAdapter mGroupRecyclerViewAdapter;
    private MyGroupLocRecyclerViewAdapter myGroupLocRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GroupFragment newInstance(int columnCount) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_list, container, false);
        ButterKnife.bind(this, view);

        mGroupPresenter = new GroupPresenter(this);

        // Customize the menu
        setHasOptionsMenu(true);

        // Set the adapter
        Context context = view.getContext();
        mGroupLayoutManager = new LinearLayoutManager(context);
        mGroupRV.setLayoutManager(mGroupLayoutManager);

        // Setting the location adapter
        mGroupRecyclerViewAdapter = new MyGroupRecyclerViewAdapter(getContext(), null);
        myGroupLocRecyclerViewAdapter = new MyGroupLocRecyclerViewAdapter(getContext(), null);

        // Scroll Listener
        scrollListener = new EndlessScrollListener(mGroupLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getData(page);
            }
        };

        // Finding the current state of locations
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getContext());
        locationIsEnabled = sharedPreferenceManager.getEnableLocationValueFromPref();

        if(locationIsEnabled){
            mGroupRV.setAdapter(myGroupLocRecyclerViewAdapter);
            getLocationData();
        }else{
            mGroupRV.setAdapter(mGroupRecyclerViewAdapter);
            getData(pageNum);
            mGroupRV.addOnScrollListener(scrollListener);
        }

        addGroupFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewGroup();
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.group_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        mLocToggleMenu = menu.findItem(R.id.nearme);

        mLocToggleMenu.setChecked(locationIsEnabled);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.nearme:
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getContext());
                sharedPreferenceManager.toggleEnableLocationInPref();
                locationIsEnabled = sharedPreferenceManager.getEnableLocationValueFromPref();
                mLocToggleMenu.setChecked(locationIsEnabled);
                locationEnablingToggled();
        }
        return super.onOptionsItemSelected(item);
    }

    /** DATA METHODS **/

    // Method that reacts to using location within the groups or not
    public void locationEnablingToggled(){
        if(locationIsEnabled){
            Toast.makeText(getContext(), "Location is enabled", Toast.LENGTH_LONG).show();
            myGroupLocRecyclerViewAdapter.mValues = null; // reset data
            getLocationData();
        }else{
            Toast.makeText(getContext(), "Location is NOT enabled", Toast.LENGTH_LONG).show();
            if(mGroupRecyclerViewAdapter.mValues == null){
                mGroupRV.addOnScrollListener(scrollListener);
                getData(pageNum);
            }else{
                mGroupRV.addOnScrollListener(scrollListener);
                mGroupRV.setAdapter(mGroupRecyclerViewAdapter);
            }
        }
    }

    public void getLocationData(){
        mGroupPresenter.getGroupsLoc();
    }

    public void getLocDataSuccess(ArrayList groups){
        myGroupLocRecyclerViewAdapter.addData(groups);
        mGroupRV.setAdapter(myGroupLocRecyclerViewAdapter);
        mGroupRV.removeOnScrollListener(scrollListener);
    }

    public void showAddedGroup(Group group){
        if(locationIsEnabled){
            myGroupLocRecyclerViewAdapter.addNewGroupData(group);
            myGroupLocRecyclerViewAdapter.notifyDataSetChanged();
        }else{
            mGroupRecyclerViewAdapter.addNewGroupData(group);
            mGroupRecyclerViewAdapter.notifyDataSetChanged();
        }

    }

    public void addNewGroup(){
        Toast.makeText(getContext(), "Making new group", Toast.LENGTH_SHORT).show();
        mGroupPresenter.checkIfLoggedIn();
    }

    @Override
    public void showLoadingState(boolean visible) {
        int visibility = (visible == true) ? View.VISIBLE: View.INVISIBLE;
        mProgressBar.setVisibility(visibility);
    }

    @Override
    public void getData(int pageNum) {
        mGroupPresenter.getGroups(pageNum);
    }

    @Override
    public void getDataSuccess(ArrayList groups) {
        mGroupRecyclerViewAdapter.addData(groups);
        mGroupRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataFailure() {
        Toast.makeText(getContext(),
                "Experiencing network errors. Please confirm you have a valid internet connection and try again.",
                Toast.LENGTH_SHORT)
                .show();
    }

    /** DIALOG METHODS **/

    public AlertDialog groupDialogCreator(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_group, null);
        final EditText groupTitle = view.findViewById(R.id.group_title_et);
        final CheckBox locationCheckbox = view.findViewById(R.id.user_location_checkbox);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setTitle("Create a Group")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Make Group with: " + groupTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                        mGroupPresenter.addNewGroup(groupTitle.getText().toString(), locationCheckbox.isChecked());
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogInterface.cancel();
                    }
                });


        return builder.create();
    }

    public AlertDialog failedUserDialogCreator(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setMessage("You are currently not signed in, which is required for this feature. Please sign in or register to continue.")
                .setTitle("Currently Not Signed in")
                .setPositiveButton("Sign in/Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent loginIntent = new Intent(getContext(), LoginView.class);
                        startActivity(loginIntent);
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogInterface.cancel();
                    }
                });


        return builder.create();
    }
}
