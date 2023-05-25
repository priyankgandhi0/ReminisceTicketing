package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginRespo {

    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("staus")
    @Expose
    public String status;
    @SerializedName("code")
    @Expose
    public Integer code;

    public class Data {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("token")
        @Expose
        public String userToken;
        @SerializedName("email")
        @Expose
        public String email;

    }
}
