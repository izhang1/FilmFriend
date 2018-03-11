package app.izhang.filmfriend.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Adapter.HomePagerFragmentAdapter;
import app.izhang.filmfriend.View.Base.BaseDataView;

public class HomeView extends AppCompatActivity implements BaseDataView {

    // UI References
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        mViewPager = findViewById(R.id.viewPager);

        HomePagerFragmentAdapter viewPagerAdapter = new HomePagerFragmentAdapter(this, getSupportFragmentManager());

        mViewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showLoadingState(boolean visible) {

    }

    @Override
    public void getData() {

    }

    @Override
    public void getDataSuccess() {

    }

    @Override
    public void getDataFailure() {

    }

    @Override
    public void searchData() {

    }

    @Override
    public void searchDataSuccess() {

    }

    @Override
    public void searchDataFailure() {

    }
}
