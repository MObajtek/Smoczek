package com.example.smoczek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button  button_login, button_register_link;
    EditText edit_login_username, edit_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_login_username = (EditText) findViewById(R.id.edit_login_username);
        edit_login_password = (EditText) findViewById(R.id.edit_login_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_register_link = (Button) findViewById(R.id.button_register_link);

        button_login.setOnClickListener(this);
        button_register_link.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:

                break;
            case R.id.button_register_link:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
