package app.izhang.filmfriend.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import app.izhang.filmfriend.R;

/**
 * Created by ivanzhang on 4/3/18.
 */

public class SharedPreferenceManager {
    public static String POSTAL_CODE_KEY = "postal_code";
    public static String USERNAME_KEY = "username_key";
    private static String ENABLE_LOCATION_KEY = "enable_location";

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

    public void saveUsernameToPref(String username){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.commit();
    }

    public String getUsernameFromPref(){
        return (mSharedPreferences.getString(USERNAME_KEY, ""));
    }

    public void toggleEnableLocationInPref(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(ENABLE_LOCATION_KEY, !getEnableLocationValueFromPref());
        editor.commit();
    }

    public boolean getEnableLocationValueFromPref(){
        return (mSharedPreferences.getBoolean(ENABLE_LOCATION_KEY, false));
    }

}
