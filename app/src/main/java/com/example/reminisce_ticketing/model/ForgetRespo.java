package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetRespo {

@SerializedName("code")
@Expose
public Integer code;
@SerializedName("status")
@Expose
public String status;
@SerializedName("message")
@Expose
public Object message;
@SerializedName("token")
@Expose
public String token;

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

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

}