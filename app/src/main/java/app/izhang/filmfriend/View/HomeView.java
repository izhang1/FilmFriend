package app.izhang.filmfriend.View;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Adapter.HomePagerFragmentAdapter;
import app.izhang.filmfriend.View.Base.BaseDataSearchView;
import app.izhang.filmfriend.View.Base.BaseDataView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeView extends AppCompatActivity implements BaseDataSearchView {

    // UI References
    @BindView(R.id.viewPager)ViewPager mViewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        ButterKnife.bind(this);

        HomePagerFragmentAdapter viewPagerAdapter = new HomePagerFragmentAdapter(this, getSupportFragmentManager());

        mViewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showLoadingState(boolean visible) {

    }

    @Override
    public void searchData() {

    }

    @Override
    public void searchDataSuccess(ArrayList searchDataList) {

    }

    @Override
    public void searchDataFailure(String failure) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }


}
