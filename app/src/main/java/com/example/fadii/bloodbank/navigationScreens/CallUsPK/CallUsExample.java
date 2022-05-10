package com.example.fadii.bloodbank.navigationScreens.CallUsPK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallUsExample {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private CallusData callusData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CallusData getData() {
        return callusData;
    }

    public void setData(CallusData data) {
        this.callusData = data;
    }
}
