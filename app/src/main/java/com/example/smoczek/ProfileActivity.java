package com.example.smoczek;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements  View.OnClickListener{

    private Button button_logout;
    private TextView view_main_email, view_main_age;
    private FirebaseAuth firebase_auth;
    private DatabaseReference database_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initializing authentication
        firebase_auth = FirebaseAuth.getInstance();

        // checking if user is logged
        if (firebase_auth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        database_reference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = firebase_auth.getCurrentUser();

        view_main_email = (TextView) findViewById(R.id.view_main_email);
        view_main_email.setText(user.getEmail());
        view_main_age = (TextView) findViewById(R.id.view_main_age);
        button_logout = (Button) findViewById(R.id.button_logout);

        button_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_logout:
                firebase_auth.signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
