package com.example.reminisce_ticketing.apiservice;



import com.example.reminisce_ticketing.model.ChangePasswordReq;
import com.example.reminisce_ticketing.model.ChangePasswordRespo;
import com.example.reminisce_ticketing.model.ErrorResponse;
import com.example.reminisce_ticketing.model.EventDetailsModel;
import com.example.reminisce_ticketing.model.EventListModel;
import com.example.reminisce_ticketing.model.ForgetRespo;
import com.example.reminisce_ticketing.model.UserLoginReq;
import com.example.reminisce_ticketing.model.UserLoginRespo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

   /* @Headers({"Accept: application/json",
            "App-Track-Version:v1",
            "App-Device-Type:iOS",
            "App-Store-Version:1.1",
            "App-Device-Model:iPhone 8",
            "App-Os-Version:iOS 11",
            "App-Store-Build-Number:1.1"})*/
//            "App-Secret:RESC5698745ravi"})
    @POST("api/stafflogin")
    Call<UserLoginRespo> userLogin(@Body UserLoginReq loginRequest);

    @GET("api/eventlist")
    Call<EventListModel> getEventList();


    @POST("api/singleeventlist")
    Call<EventDetailsModel> getEventDetails(@Body UserLoginReq loginRequest);

    @POST("api/updatepassword")
    Call<ChangePasswordRespo> changePassword(@Body ChangePasswordReq changePasswordReq);


    @POST("api/forgotpassword")
    Call<ForgetRespo> forgetPassword(@Body UserLoginReq userLoginReq);

    @POST("api/logout")
    Call<ResponseBody> logOut();

}
