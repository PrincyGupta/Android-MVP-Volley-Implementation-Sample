package com.example.admin5.mvpandvolley.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin5.mvpandvolley.models.login.LoginResponse;
import com.example.admin5.mvpandvolley.viewops.LoginView;
import com.google.gson.Gson;

public class LoginPresenter {

    private final String TAG = getClass().getSimpleName();

    private LoginView loginView;
    private Context context;

    public LoginPresenter(LoginView loginView, Context context) {

        this.loginView = loginView;
        this.context = context;
    }

    public void login(String phoneNumber, String password) {

        if (TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(password)) {
            loginView.showValidationError();
        } else {
            final String url = "http://192.168.0.115:8888/adel-2017/web-services/customer/ws_login.php?function=login&customer_phone_number="
                    + phoneNumber + "&customer_password="
                    + password + "&customer_device_id=11";
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();

                    LoginResponse data = gson.fromJson(response, LoginResponse.class);
                    if (data.getStatus().equals("success")) {

                        Log.d(TAG, "success");

//                        Toast.makeText(context, data.getData().getItem().getCustId(), Toast.LENGTH_LONG).show();
//                        Toast.makeText(context, data.getData().getItem().getSessionKey(), Toast.LENGTH_LONG).show();
                        loginView.loginSuccess();
                    }
                    else {
                        Log.d(TAG, "failed > Status: " + data.getStatus());
                        loginView.loginError();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Failure : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    loginView.loginError();
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(stringRequest);

        }
    }
}