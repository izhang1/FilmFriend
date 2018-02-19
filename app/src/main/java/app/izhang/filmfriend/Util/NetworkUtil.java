package app.izhang.filmfriend.Util;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.izhang.filmfriend.BuildConfig;
import app.izhang.filmfriend.Model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivanzhang on 2/19/18.
 */

public class NetworkUtil{

    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static final String MOVIE_DB_KEY = BuildConfig.MOVIE_DB_KEY;

    public void testCallMovieAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<MovieJsonResponse> moviesCall = service.topRatedMovieList(MOVIE_DB_KEY, 1);
        moviesCall.enqueue(new Callback<MovieJsonResponse>() {
            @Override
            public void onResponse(Call<MovieJsonResponse> call, Response<MovieJsonResponse> response) {
                if(response.isSuccessful()) {
                    MovieJsonResponse jsonResponse = response.body();
                    ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));
                    for(Movie m : movies){
                        System.out.println(m.toString());
                    }
                }else{
                    System.err.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MovieJsonResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }



}
