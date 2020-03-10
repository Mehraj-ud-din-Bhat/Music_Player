package com.example.demoapplication.API;



import com.example.demoapplication.Modal.Song;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

//    @FormUrlEncoded
//    @POST("Login")
//    Call<LoginResponse> userLogin(
//            @Field("Username") String email,
//            @Field("Password") String password
//    );
//
//

    @GET("songs")
    Call<List<Song>> getSongs();

}
