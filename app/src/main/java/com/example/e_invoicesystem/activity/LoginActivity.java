package com.example.e_invoicesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_invoicesystem.R;

public class LoginActivity extends AppCompatActivity {
    public Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
    }

    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, NavActivity.class);
                // startActivity(intent);
                checkUsername();
            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return true;
    }

    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(username)) {
            username.setError("You must enter username to login!");
            isValid = true;
        } else {
            if (!isEmpty(username)) {
                username.setError("Enter valid Username!");
                isValid = true;
            }
        }

        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = true;
        } else {
            if (password.getText().toString().length() < 10) {
                password.setError("Password must be at least 4 chars long!");
                isValid = true;
            }
        }

        //check email and password
        //IMPORTANT: here should be call to backend or safer function for local check; For example simple check is cool
        //For example simple check is cool
        if (isValid) {
            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();
            if (usernameValue.equals("") && passwordValue.equals("")) ;

            //everything checked we open new activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            //we close this activity
            this.finish();
        } else {
            Toast t = Toast.makeText(this, "Wrong Username or password!", Toast.LENGTH_SHORT);
            t.show();


        }
    }
}