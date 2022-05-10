package com.example.fadii.bloodbank.mainUIPK.Article_Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 10/27/2018.
 */

public class PostsDetails_Example {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private PostsDetails_Data data;

    public PostsDetails_Example(Integer status, String msg, PostsDetails_Data data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

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

    public PostsDetails_Data getData() {
        return data;
    }

    public void setData(PostsDetails_Data data) {
        this.data = data;
    }
}
