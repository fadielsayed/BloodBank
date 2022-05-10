package com.example.fadii.bloodbank.navigationScreens.ModifyUserDataPK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModifyData {
    @SerializedName("pin_code_for_test")
    @Expose
    private Integer pinCodeForTest;

    public Integer getPinCodeForTest() {
        return pinCodeForTest;
    }

    public void setPinCodeForTest(Integer pinCodeForTest) {
        this.pinCodeForTest = pinCodeForTest;
    }
}
