package app.izhang.filmfriend.Util;

import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;

import app.izhang.filmfriend.BuildConfig;
import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.Model.Services.MovieJsonResponse;
import app.izhang.filmfriend.Model.Services.TheMovieDBService;
import app.izhang.filmfriend.Model.Services.ZipCodeService;
import app.izhang.filmfriend.Model.Services.ZipJsonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivanzhang on 2/19/18.
 */

public class NetworkUtil{

    private static final String MOVIE_DB_KEY = BuildConfig.MOVIE_DB_KEY;
    private static final String LOC_ZIP_KEY = BuildConfig.LOC_ZIP_KEY;

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String BASE_LOC_URL = "https://www.zipcodeapi.com/rest/"+ LOC_ZIP_KEY +"/radius.json/";

    private static Retrofit retrofitInstance = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitLocInstance = new Retrofit.Builder()
            .baseUrl(BASE_LOC_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofitInstance(){
        return retrofitInstance;
    }

    public static Retrofit getRetrofitLocInstance(){
        return retrofitLocInstance;
    }

    public static void testZipLocApi(){
        Retrofit retrofit = getRetrofitLocInstance();

        ZipCodeService service = retrofit.create(ZipCodeService.class);
        Call<ZipJsonResponse> zipCodeCall = service.getZipCodesByRadius("28217", "20");
        zipCodeCall.enqueue(new Callback<ZipJsonResponse>() {
            @Override
            public void onResponse(Call<ZipJsonResponse> call, Response<ZipJsonResponse> response) {
                if(response.isSuccessful()){
                    ZipJsonResponse zipResponse = response.body();
                    ArrayList<String> zipCodes = new ArrayList<>(Arrays.asList(zipResponse.getResults()));
                    for(String zips: zipCodes){
                        Log.v("ZipCode: ", zips);
                    }

                }else{
                    Log.v("Call: ", call.toString());
                    Log.v("Not Successful: ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ZipJsonResponse> call, Throwable t) {
                Log.v("Call: ", call.toString());
                Log.v("Not Successful: ", "Call Failed \n");
                t.printStackTrace();
            }
        });
    }

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

    public void testCallPopularMovieList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<MovieJsonResponse> moviesCall = service.popularMovieList(MOVIE_DB_KEY, 1);
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

    public void testSearchMovie(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<MovieJsonResponse> moviesCall = service.searchMovie(MOVIE_DB_KEY, "black panther");
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

    // Movie ID: 284054 - Black Panther 2018 Version
    public void testGetMovieId(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<Movie> moviesCall = service.getMovieDetails("284054", MOVIE_DB_KEY);
        moviesCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()) {
                    Movie movie = response.body();
                    System.out.println(movie);
                }else{
                    System.err.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }



}
