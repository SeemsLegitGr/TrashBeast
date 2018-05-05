package gr.seemslegit.trashbeast.Client;

import java.util.List;

import gr.seemslegit.trashbeast.Models.Village;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VillageClient {

    @GET("getAllVillages")
    Call<List<Village>> getAll();

    @POST("addVillage")
    Call<Village> create(@Body Village village);
}
