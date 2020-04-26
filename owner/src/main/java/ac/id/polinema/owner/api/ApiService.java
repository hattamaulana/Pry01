package ac.id.polinema.owner.api;

import java.util.List;

import ac.id.polinema.owner.model.TransactionModel;
import ac.id.polinema.owner.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("/admin/login")
    Call<Response<UserModel>> login(@Field("noHp") String no_handphone,
                                    @Field("password") String password);

    @GET("/admin/order/new")
    Call<Response<List<TransactionModel>>> getNewOrder();

    @GET("/admin/order/on_proggress")
    Call<Response<List<TransactionModel>>> getOrderOnProggress();

    @GET("/admin/order/history")
    Call<Response<List<TransactionModel>>> getOrderHistory();

    @FormUrlEncoded
    @PUT("/admin/order/:nota")
    Call<Response<Boolean>> update(@Path("noNota") String nota,
                                   @Field("statusPengerjaan") String status);

}
