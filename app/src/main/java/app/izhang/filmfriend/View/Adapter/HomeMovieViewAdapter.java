package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.R;


/**
 * Created by ivanzhang on 3/3/18.
 */

public class HomeMovieViewAdapter extends RecyclerView.Adapter<HomeMovieViewAdapter.ViewHolder>{
    private final List<Movie> mValues;
    private final Context mContext;

    public HomeMovieViewAdapter(Context context, List<Movie> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public HomeMovieViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new HomeMovieViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeMovieViewAdapter.ViewHolder holder, int position) {
        // TODO: 3/3/18 Add the appropirate date for this specific movie 
        // holder.mMovieTitle.setText(mValues.get(position).id);
        // holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        // TODO: 3/3/18 Change this to return 0 once we are pulling in data
        if(mValues == null) return 1;
        else return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mMovieTitle;
        public final TextView mMovieDescription;
        public final ImageView mMoviePoster;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMovieTitle =  view.findViewById(R.id.tv_movie_title);
            mMovieDescription = view.findViewById(R.id.tv_group_lastmsg);
            mMoviePoster = view.findViewById(R.id.iv_movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMovieDescription.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            // todo: Change this to go to the movie detail screen
            // Intent testGroupIntent = new Intent(mContext, GroupDetailView.class);
            // mContext.startActivity(testGroupIntent);
        }
    }
}
