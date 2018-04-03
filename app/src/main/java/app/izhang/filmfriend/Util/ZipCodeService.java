package app.izhang.filmfriend.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by ivanzhang on 4/2/18.
 */

public interface ZipCodeService {

    // https://www.zipcodeapi.com/rest/<api_key>/radius.csv/<ZIPCode>/<Miles>/miles?minimal
    @GET("{zipcode}/{miles}/miles?minimal")
    Call<ZipJsonResponse> getZipCodesByRadius(@Path("zipcode") String zipcode, @Path("miles") String miles);
}
