package app.izhang.filmfriend.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;

import app.izhang.filmfriend.Model.Movie;

/**
 * Created by ivanzhang on 2/19/18.
 */

public class MovieJsonResponse {
    private Movie[] results;

    public Movie[] getResults(){
        return results;
    }
}
