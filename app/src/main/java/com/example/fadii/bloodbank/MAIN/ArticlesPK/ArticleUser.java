package com.example.fadii.bloodbank.MAIN.ArticlesPK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleUser {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ArticleData articleData;

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

    public ArticleData getData() {
        return articleData;
    }

    public void setData(ArticleData articleData) {
        this.articleData = articleData;
    }
}
