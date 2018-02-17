package app.izhang.filmfriend.View.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.izhang.filmfriend.View.GroupFragment;
import app.izhang.filmfriend.View.HomeMovieFragment;
import app.izhang.filmfriend.View.SavedFragment;

/**
 * Created by ivanzhang on 2/17/18.
 */

public class HomePagerFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public HomePagerFragmentAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeMovieFragment();
        } else if (position == 1){
            return new GroupFragment();
        } else if (position == 2){
            return new SavedFragment();
        } else {
            return new HomeMovieFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Groups";
            case 2:
                return "Favorites";
            default:
                return null;
        }
    }

}