package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordReq {
        @SerializedName("old_password")
        @Expose
        public String oldPassword;
        @SerializedName("new_password")
        @Expose
        public String newPassword;

        @SerializedName("confirm_password")
        @Expose
        public String confirmPassword;

}
