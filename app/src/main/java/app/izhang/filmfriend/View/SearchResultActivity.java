package app.izhang.filmfriend.View;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import app.izhang.filmfriend.Presenter.HomePresenter;
import app.izhang.filmfriend.Presenter.SearchPresenter;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Adapter.HomeMovieViewAdapter;
import app.izhang.filmfriend.View.Base.BaseDataSearchView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity implements BaseDataSearchView {

    public static final String SEARCH_KEY = "search_key";

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.movies_rv) RecyclerView mMovieSearchRV;

    private SearchPresenter mSearchPresenter;
    private LinearLayoutManager mMovieLayoutManager;
    private HomeMovieViewAdapter mSearchViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        String searchQuery = getIntent().getStringExtra(SEARCH_KEY);

        // Setup action bar
        setTitle("Searching for " + searchQuery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchPresenter = new SearchPresenter(this);

        mMovieLayoutManager = new LinearLayoutManager(this);
        mMovieSearchRV.setLayoutManager(mMovieLayoutManager);
        mSearchViewAdapter = new HomeMovieViewAdapter(this, null);
        mMovieSearchRV.setAdapter(mSearchViewAdapter);
        searchData(searchQuery);
    }

    @Override
    public void showLoadingState(boolean visible) {
        int visibility = (visible == true) ? View.VISIBLE: View.INVISIBLE;
        mProgressBar.setVisibility(visibility);
    }

    @Override
    public void searchData(String searchQuery) {
        mSearchPresenter.searchMovie(searchQuery);
    }

    @Override
    public void searchDataSuccess(ArrayList searchDataList) {
        mSearchViewAdapter.addData(searchDataList);
        mSearchViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void searchDataFailure(String failure) {
        Toast.makeText(this, "Error with searching, please try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
