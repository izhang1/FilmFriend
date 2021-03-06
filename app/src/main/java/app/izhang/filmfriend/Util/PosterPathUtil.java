package app.izhang.filmfriend.Util;

/**
 * Created by ivanzhang on 3/17/18.
 *
 * - PosterPathUtils class
 * - Utility class that will help create the URL
 * - Moving to create a more dynamic approach based on the size of the screen.
 */

public class PosterPathUtil {

    private static String BASE_URL = "http://image.tmdb.org/t/p/";

    // Different Size Params
    private static String SIZE_W92 = "w92";
    private static String SIZE_W154 = "w154";
    private static String SIZE_W185 = "w185";
    private static String SIZE_W342 = "w342";
    private static String SIZE_W500 = "w500";
    private static String SIZE_W780 = "w780";
    private static String SIZE_ORIGINAL = "original";


    /**
     * buildPosterUrl method
     * - Takes in a path and size, returns a built string that can be queried to retrieve the poster image
     * @param path
     * @return
     */
    public static String buildPosterURL(String path){
        // Defaulting size to W500
        return (BASE_URL + SIZE_W500 + "/" + path);
    }

}