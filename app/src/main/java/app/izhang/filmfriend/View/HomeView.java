package app.izhang.filmfriend.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.LocationUtil;
import app.izhang.filmfriend.View.Adapter.HomePagerFragmentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeView extends AppCompatActivity {

    // UI References
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        ButterKnife.bind(this);

        HomePagerFragmentAdapter viewPagerAdapter = new HomePagerFragmentAdapter(this, getSupportFragmentManager());

        mViewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

        setTitle(getResources().getString(R.string.app_name));

        checkForLocationPermissions();
    }


    private void checkForLocationPermissions() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);
        boolean initialAskForLocation = sharedPref.getBoolean(LocationUtil.INITIAL_LOCATION_ASKED_KEY, false);

        // if we already asked for permissions, don't ask again
        if (!initialAskForLocation) {
            LocationUtil.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LocationUtil.PERMISSION_LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        LocationUtil.gatherZipCode(this);
                } else {
                    Toast.makeText(this, "Permission denied: No location features will be used", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }



}
