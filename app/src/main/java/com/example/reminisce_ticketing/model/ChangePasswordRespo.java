package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChangePasswordRespo {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public Object message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public class Message {

        @SerializedName("old_password")
        @Expose
        public List<String> oldPassword;
        @SerializedName("new_password")
        @Expose
        public List<String> newPassword;
        @SerializedName("confirm_password")
        @Expose
        public List<String> confirmPassword;

        public List<String> getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(List<String> oldPassword) {
            this.oldPassword = oldPassword;
        }

        public List<String> getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(List<String> newPassword) {
            this.newPassword = newPassword;
        }

        public List<String> getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(List<String> confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

    }
}