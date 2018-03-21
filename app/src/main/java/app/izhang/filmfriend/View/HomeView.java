package app.izhang.filmfriend.View;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

public class HomeView extends AppCompatActivity{

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



}
