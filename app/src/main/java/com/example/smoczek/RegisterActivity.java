package com.example.smoczek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_register;
    private EditText edit_register_username, edit_register_password, edit_register_age,
            edit_register_email;
    private FirebaseAuth firebase_auth;
    private DatabaseReference database_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebase_auth = FirebaseAuth.getInstance();

        database_reference = FirebaseDatabase.getInstance().getReference();


        edit_register_username = (EditText) findViewById(R.id.edit_register_username);
        edit_register_email = (EditText) findViewById(R.id.edit_register_email);
        edit_register_age = (EditText) findViewById(R.id.edit_register_age);
        edit_register_password = (EditText) findViewById(R.id.edit_register_password);
        button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(this);
    }

    private void registerUser(){
        final String email = edit_register_email.getText().toString().trim();
        String password = edit_register_password.getText().toString().trim();
        final String username = edit_register_username.getText().toString().trim();
        final int age = Integer.parseInt(edit_register_age.getText().toString().trim());

        if (TextUtils.isEmpty(email)){
            return;
        }
        if (TextUtils.isEmpty(password)){
            return;
        }

        firebase_auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (task.isSuccessful()) {


                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            if (firebase_auth.getCurrentUser() != null){

                                // save user info in the database
                                UserInformation user_information = new UserInformation(email,username,age);
                                FirebaseUser user = firebase_auth.getCurrentUser();
                                database_reference.child(user.getUid()).setValue(user_information);
                                database_reference.push();

                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Could not register",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_register:
                registerUser();
                break;
        }
    }
}
