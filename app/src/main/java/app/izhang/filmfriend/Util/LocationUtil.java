package app.izhang.filmfriend.Util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import app.izhang.filmfriend.Model.SharedPreferenceManager;

/**
 * Created by ivanzhang on 3/24/18.
 */

public class LocationUtil {
    public static String INITIAL_LOCATION_ASKED_KEY = "initial_ask";
    public final static int PERMISSION_LOCATION_REQUEST = 1001;

    public static void requestLocationPermissions(Activity activity){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
            Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_LOCATION_REQUEST);
        }
    }

    @SuppressLint("MissingPermission")
    public static void gatherZipCode(final Activity activity){
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(activity.getApplicationContext());
                            sharedPreferenceManager.saveZipCodeToPref(postalCode);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

}
