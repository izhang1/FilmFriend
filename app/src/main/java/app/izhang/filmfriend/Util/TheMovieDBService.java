package app.izhang.filmfriend.Util;

import java.util.List;

import app.izhang.filmfriend.Model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivanzhang on 2/19/18.
 */

public interface TheMovieDBService {

    // http://api.themoviedb.org/3/movie/popular?api_key=[key]
    @GET("top_rated")
    Call<MovieJsonResponse> topRatedMovieList(@Query("api_key") String key, @Query("page") int pageNum);

    @GET("popular")
    Call<List<Movie>> popularMovieList();

}
