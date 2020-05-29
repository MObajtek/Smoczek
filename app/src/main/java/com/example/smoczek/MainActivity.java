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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_login, button_register_link;
    private EditText edit_login_email, edit_login_password;
    private FirebaseAuth firebase_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase_auth = FirebaseAuth.getInstance();

        if (firebase_auth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        edit_login_email = (EditText) findViewById(R.id.edit_login_email);
        edit_login_password = (EditText) findViewById(R.id.edit_login_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_register_link = (Button) findViewById(R.id.button_register_link);

        button_login.setOnClickListener(this);
        button_register_link.setOnClickListener(this);
    }

    private void loginUser(){
        String email = edit_login_email.getText().toString().trim();
        String password = edit_login_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            return;
        }
        if (TextUtils.isEmpty(password)){
            return;
        }

        firebase_auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Logged in successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    }

                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                loginUser();
                break;
            case R.id.button_register_link:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
