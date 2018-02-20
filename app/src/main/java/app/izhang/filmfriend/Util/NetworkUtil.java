package app.izhang.filmfriend.Util;

import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;

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

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

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
