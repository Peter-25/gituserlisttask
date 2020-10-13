package com.example.userdetail.apicall;

import com.google.gson.JsonObject;

public interface ResponseCallback {
    void onSuccess(JsonObject result) throws Exception;
    void onError(Throwable error) throws Exception;
}
