package com.example.fadii.bloodbank.navigationScreens.ModifyUserDataPK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModifyExample {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ModifyData modifyData;

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

    public ModifyData getData() {
        return modifyData;
    }

    public void setData(ModifyData data) {
        this.modifyData = data;
    }
}
