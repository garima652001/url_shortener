package users.com.cuttly.Interface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import users.com.cuttly.Response.UrlResponse;

public interface Api {

    @GET("api.php")
    Call<UrlResponse> shorten(@Query("key") String key,
                              @Query("short") String url
            );
}
