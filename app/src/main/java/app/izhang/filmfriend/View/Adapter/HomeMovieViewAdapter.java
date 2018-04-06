package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.PosterPathUtil;
import app.izhang.filmfriend.View.MovieDetailView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ivanzhang on 3/3/18.
 */

public class HomeMovieViewAdapter extends RecyclerView.Adapter<HomeMovieViewAdapter.ViewHolder>{
    private ArrayList<Movie> mValues;
    private Context mContext;

    public HomeMovieViewAdapter(Context context, ArrayList<Movie> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public HomeMovieViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new HomeMovieViewAdapter.ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(final HomeMovieViewAdapter.ViewHolder movieHolder, int position) {
        Movie tempMovie = mValues.get(position);
        String posterPath = PosterPathUtil.buildPosterURL(tempMovie.getPoster_path());
        Glide.with(movieHolder.mView)
                .load(posterPath)
                .into(movieHolder.mMoviePoster);

        movieHolder.mMovieTitle.setText(tempMovie.getTitle());
        movieHolder.mMovieRating.setText(mContext.getString(R.string.movie_rating_label) + tempMovie.getVote_average());
    }

    @Override
    public int getItemCount() {
        if(mValues == null) return 0;
        else return mValues.size();
    }

    public void addData(ArrayList<Movie> movies){
        // If null, replace the contents. Otherwise, add to it.
        if(mValues == null) this.mValues = movies;
        else{
            this.mValues.addAll(movies);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        @BindView(R.id.tv_movie_title) public TextView mMovieTitle;
        @BindView(R.id.tv_rating) public TextView mMovieRating;
        @BindView(R.id.iv_movie_poster) public ImageView mMoviePoster;
        private final Context vhContext;

        public ViewHolder(View view, Context context) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
            vhContext = context;
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMovieTitle.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            // todo: Change this to go to the movie detail screen and pass in associated movie
            Intent testMovieDetailIntent = new Intent(vhContext, MovieDetailView.class);
            vhContext.startActivity(testMovieDetailIntent);
        }
    }
}
