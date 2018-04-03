package app.izhang.filmfriend.Model;

import android.content.Context;
import android.content.SharedPreferences;

import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.LocationUtil;

/**
 * Created by ivanzhang on 4/3/18.
 */

public class SharedPreferenceManager {
    public static String POSTAL_CODE_KEY = "postal_code";

    private SharedPreferences mSharedPreferences;

    public SharedPreferenceManager(Context context){
        mSharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file_key), Context.MODE_PRIVATE);
    }

    public void saveZipCodeToPref(String postalCode){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(POSTAL_CODE_KEY, postalCode);
        editor.commit();
    }

    public String getZipCodeFromPref(){
        return (mSharedPreferences.getString(POSTAL_CODE_KEY, ""));
    }

}
