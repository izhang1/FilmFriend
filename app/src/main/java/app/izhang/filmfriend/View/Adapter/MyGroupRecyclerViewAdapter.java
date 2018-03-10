package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.GroupDetailView;
import app.izhang.filmfriend.View.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class MyGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder> {

    private final List<Group> mValues;
    private final Context mContext;

    public MyGroupRecyclerViewAdapter(Context context, List<Group> items) {
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
        holder.mGroupLastMessage.setText(mValues.get(position).getOwner());
    }

    @Override
    public int getItemCount() {
        if(mValues != null){
            return mValues.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mGroupTitle;
        public final TextView mGroupLastMessage;
        private final Context vhContext;
        public Group mGroup;

        public ViewHolder(View view,Context context){
            super(view);
            mView = view;
            mGroupTitle =  view.findViewById(R.id.tv_movie_title);
            mGroupLastMessage = view.findViewById(R.id.tv_group_lastmsg);
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