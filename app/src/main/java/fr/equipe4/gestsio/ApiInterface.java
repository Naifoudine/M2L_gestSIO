package fr.equipe4.gestsio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @GET("Api.php?")
    Call<List<Appreciation>> getAppreciations(@Query("id") int id);
}
