package com.example.smoczek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button button_register;
    EditText edit_register_username, edit_rgister_password, edit_register_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edit_register_username = (EditText) findViewById(R.id.edit_register_username);
        edit_register_age = (EditText) findViewById(R.id.edit_register_age);
        edit_rgister_password = (EditText) findViewById(R.id.edit_register_password);
        button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_register:
                break;
        }
    }
}
