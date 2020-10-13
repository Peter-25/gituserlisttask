package com.example.userdetail.apicall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonObject;

public class ResponseValue {
    private String userName = "";
    private String password = "", status = "";


    @Nullable
    public JsonObject data = new JsonObject();

    @Nullable
    public Throwable error = new Exception();



    public static ResponseValue success(@NonNull JsonObject data) {
        return new ResponseValue("Success", data);
    }

    public static ResponseValue Error(String Status, @NonNull Throwable error) {
        return new ResponseValue("Error", error);
    }


    public ResponseValue(String status, JsonObject jsonObject) {
        this.status = status;
        this.data = jsonObject;
    }

    public ResponseValue(String status, Throwable throwable) {
        this.status = status;
        this.error = throwable;
    }



    public String getStatus() {
        return status;
    }



}

