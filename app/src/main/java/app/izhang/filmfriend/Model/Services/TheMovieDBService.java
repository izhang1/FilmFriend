package app.izhang.filmfriend.Model.Services;

import app.izhang.filmfriend.Model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivanzhang on 2/19/18.
 */

public interface TheMovieDBService {

    // http://api.themoviedb.org/3/movie/popular?api_key=[key]
    // http://api.themoviedb.org/3/search/movie?api_key=6b686c328bd2f8bb5a4fd7e4d526bce6&query=blackpanther
    @GET("movie/top_rated")
    Call<MovieJsonResponse> topRatedMovieList(@Query("api_key") String key, @Query("page") int pageNum);

    @GET("movie/popular")
    Call<MovieJsonResponse> popularMovieList(@Query("api_key") String key, @Query("page") int pageNum);

    @GET("search/movie")
    Call<MovieJsonResponse> searchMovie(@Query("api_key") String key, @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") String movieId, @Query("api_key") String key);


}
