package com.example.admin5.mvpandvolley.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin5 on 15-May-17.
 */

public class LoginData {
    @SerializedName("posts")
    @Expose
    private LoginItem item;

    public LoginItem getItem() {
        return item;
    }

    public void setItem(LoginItem item) {
        this.item = item;
    }
}
