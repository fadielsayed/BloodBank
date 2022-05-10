package com.example.fadii.bloodbank.mainUIPK.NotifyPK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notify_Example {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Notify_Data notify_data;

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

    public Notify_Data getData() {
        return notify_data;
    }

    public void setData(Notify_Data data) {
        this.notify_data = data;
    }

}
