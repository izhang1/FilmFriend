package app.izhang.filmfriend.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.Util.FirebaseService;
import app.izhang.filmfriend.Util.MovieJsonResponse;
import app.izhang.filmfriend.Util.NetworkUtil;
import app.izhang.filmfriend.Util.TheMovieDBService;
import app.izhang.filmfriend.View.HomeMovieFragment;
import app.izhang.filmfriend.View.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.izhang.filmfriend.BuildConfig.MOVIE_DB_KEY;

/**
 * Created by ivanzhang on 3/10/18.
 */

public class HomePresenter {
    private HomeMovieFragment mView;

    public HomePresenter(HomeMovieFragment view){
        this.mView = view;
    }

    public void getMovieData(int pageNum){
        TheMovieDBService service = NetworkUtil.getRetrofitInstance().create(TheMovieDBService.class);
        Call<MovieJsonResponse> moviesCall = service.popularMovieList(MOVIE_DB_KEY, pageNum);
        moviesCall.enqueue(new Callback<MovieJsonResponse>() {
            @Override
            public void onResponse(Call<MovieJsonResponse> call, Response<MovieJsonResponse> response) {
                if(response.isSuccessful()) {
                    MovieJsonResponse jsonResponse = response.body();
                    ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));
                    onDataResult(true, movies);
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

    public void onDataResult(boolean success, ArrayList<Movie> movies){
        if(success){
            mView.getDataSuccess(movies);
        }else{
            mView.getDataFailure();
        }
        mView.showLoadingState(false);

    }
}
