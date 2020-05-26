package com.example.smoczek;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    Button button_logout;
    EditText edit_register_username, edit_rgister_password, edit_register_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_register_username = (EditText) findViewById(R.id.edit_register_username);
        edit_register_age = (EditText) findViewById(R.id.edit_register_age);
        edit_rgister_password = (EditText) findViewById(R.id.edit_register_password);

        button_logout = (Button) findViewById(R.id.button_logout);

        button_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_logout:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
