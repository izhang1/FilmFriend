package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.GroupDetailView;
import app.izhang.filmfriend.View.dummy.DummyContent;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyContent.DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class MyGroupLocRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupLocRecyclerViewAdapter.ViewHolder> {

    public List<Group> mValues;
    private final Context mContext;

    public MyGroupLocRecyclerViewAdapter(Context context, List<Group> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mGroup = mValues.get(position);
        holder.mGroupTitle.setText(mValues.get(position).getTitle());
        // TODO: 4/1/18 Pull the last message and display it
        //holder.mGroupLastMessage.setText(mValues.get(position).getMessages());
    }

    @Override
    public int getItemCount() {
        if(mValues != null){
            return mValues.size();
        }else{
            return 0;
        }
    }

    public void addData(ArrayList groups){
        if(mValues == null) this.mValues = groups;
        else if(groups != null){
            this.mValues.addAll(groups);
        }
    }

    public void addNewGroupData(Group group){
        if(mValues == null){
            ArrayList groups = new ArrayList();
            groups.add(group);
            this.mValues = groups;
        }else{
            // Add to front of list
            mValues.add(0, group);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        @BindView(R.id.tv_movie_title)
        TextView mGroupTitle;
        @BindView(R.id.tv_group_lastmsg) TextView mGroupLastMessage;
        private final Context vhContext;
        public Group mGroup;

        public ViewHolder(View view,Context context){
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
            vhContext = context;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent testGroupIntent = new Intent(vhContext, GroupDetailView.class);
            vhContext.startActivity(testGroupIntent);
        }
    }
}
