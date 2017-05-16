package com.example.admin5.mvpandvolley.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin5.mvpandvolley.R;
import com.example.admin5.mvpandvolley.presenters.LoginPresenter;
import com.example.admin5.mvpandvolley.viewops.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    EditText passwordEditText, phoneEditText;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordEditText = (EditText) findViewById(R.id.password);
        phoneEditText = (EditText) findViewById(R.id.phonenumber);
        submit= (Button) findViewById(R.id.login_submit);

        presenter = new LoginPresenter(this, getBaseContext());

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.login(
                        phoneEditText.getText().toString().trim(),
                        passwordEditText.getText().toString().trim()
                );
            }
        });

    }

    @Override
    public void showValidationError() {
        Toast.makeText(this, "Please enter valid passwordEditText and password!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "You are succussfully logged in!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Invalid login credintails!", Toast.LENGTH_SHORT).show();
    }
}

