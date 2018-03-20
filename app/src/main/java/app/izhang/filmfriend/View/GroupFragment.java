package app.izhang.filmfriend.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Adapter.MyGroupRecyclerViewAdapter;
import app.izhang.filmfriend.View.dummy.DummyContent;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.intentservice.chatui.fab.FloatingActionButton;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class GroupFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    @BindView(R.id.group_rv) RecyclerView mGroupRV;
    @BindView(R.id.fab) FloatingActionButton addGroupFAB;

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

        // Set the adapter
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            mGroupRV.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mGroupRV.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        mGroupRV.setAdapter(new MyGroupRecyclerViewAdapter(getContext(), getDummyData()));

        addGroupFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewGroup();
            }
        });

        return view;
    }

    public void addNewGroup(){
        // TODO Show alert dialog to ask user whether they want to utilize their location and add a name
        // TODO Create the new group and start a new intent for the user, requery for the group data list as well
    }

    // TODO: 3/4/18 Remove this after confirming the data shows
    public ArrayList getDummyData(){
        ArrayList list = new ArrayList();
        Group group1 = new Group("Title 1", null, "Ivan", "1");
        Group group2 = new Group("Title 2", null, "Ivan", "2");

        list.add(group1);
        list.add(group2);

        return list;
    }




}
