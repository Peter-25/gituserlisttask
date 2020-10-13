package com.example.userdetail.apicall;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface APIService {

    @FormUrlEncoded
    @POST(".")
    Call<JsonObject> apiCall(
            @FieldMap Map<String, Object> body);

    @GET("/repositories")
    Call<JsonArray> getUser();

}
