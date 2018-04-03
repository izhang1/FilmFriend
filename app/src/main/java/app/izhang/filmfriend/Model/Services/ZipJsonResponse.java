package app.izhang.filmfriend.Model.Services;

import app.izhang.filmfriend.Model.Movie;

/**
 * Created by ivanzhang on 4/2/18.
 */

public class ZipJsonResponse {
    private String[] zip_codes;

    public String[] getResults(){
        return zip_codes;
    }
}
