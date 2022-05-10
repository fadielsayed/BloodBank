package com.example.fadii.bloodbank.mainUIPK.DonationDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donator_Example {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Donator_Data data;

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

    public Donator_Data getData() {
        return data;
    }

    public void setData(Donator_Data data) {
        this.data = data;
    }

}
