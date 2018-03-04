package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.izhang.filmfriend.Model.Group;
import app.izhang.filmfriend.R;


/**
 * Created by ivanzhang on 3/3/18.
 */

public class SavedViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List mValues; // list of objects and
    private final Context mContext;

    public SavedViewAdapter(Context context, List items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View groupItemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.group_item, parent, false);
                return new MyGroupRecyclerViewAdapter.ViewHolder(groupItemView, mContext);
            default:
                View movieItemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.movie_item, parent, false);
                return new HomeMovieViewAdapter.ViewHolder(movieItemView, mContext);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // TODO: 3/3/18 Add the appropirate date for this specific movie
        switch (holder.getItemViewType()){
            case 1:
                MyGroupRecyclerViewAdapter.ViewHolder groupHolder = (MyGroupRecyclerViewAdapter.ViewHolder) holder;
                Group currentGroup = (Group) mValues.get(position);
                groupHolder.mGroupTitle.setText(currentGroup.getTitle());
                groupHolder.mGroupLastMessage.setText(currentGroup.getOwner());
        }
    }

    @Override
    public int getItemCount() {
        // TODO: 3/3/18 Change this to return 0 once we are pulling in data
        if(mValues == null) return 1;
        else return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if(mValues.get(position) instanceof Group){
            return 1;
        }else{
            return 0;
        }
    }
}
