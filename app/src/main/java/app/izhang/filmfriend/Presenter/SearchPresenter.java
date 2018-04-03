package app.izhang.filmfriend.Presenter;

import java.util.ArrayList;
import java.util.Arrays;

import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.Model.Services.MovieJsonResponse;
import app.izhang.filmfriend.Util.NetworkUtil;
import app.izhang.filmfriend.Model.Services.TheMovieDBService;
import app.izhang.filmfriend.View.SearchResultActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.izhang.filmfriend.BuildConfig.MOVIE_DB_KEY;

/**
 * Created by ivanzhang on 3/18/18.
 */

public class SearchPresenter {
    private SearchResultActivity mView;

    public SearchPresenter(SearchResultActivity view){
        this.mView = view;
    }

    public void searchMovie(String searchQuery){
        mView.showLoadingState(true);
        TheMovieDBService service = NetworkUtil.getRetrofitInstance().create(TheMovieDBService.class);
        Call<MovieJsonResponse> moviesCall = service.searchMovie(MOVIE_DB_KEY, searchQuery);
        moviesCall.enqueue(new Callback<MovieJsonResponse>() {
            @Override
            public void onResponse(Call<MovieJsonResponse> call, Response<MovieJsonResponse> response) {
                if(response.isSuccessful()) {
                    MovieJsonResponse jsonResponse = response.body();
                    ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));
                    onDataResult(true, movies);
                }else{
                    onDataResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<MovieJsonResponse> call, Throwable t) {
                onDataResult(false, null);
            }
        });
    }

    public void onDataResult(boolean success, ArrayList<Movie> movies){
        if(success){
            mView.searchDataSuccess(movies);
        }else{
            mView.searchDataFailure(null);
        }
        mView.showLoadingState(false);
    }
}
