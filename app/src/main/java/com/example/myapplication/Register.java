package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private Button btnlog;
    private Bundle bundle1;
    private EditText tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnlog = (Button) findViewById(R.id.btnLogin);
        tvName = (EditText) findViewById(R.id.txtName);
        bundle1 = new Bundle();
        TextView login = (TextView) findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        //Todo
        login.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
       LogingIn();

    }


    private void LogingIn() {
        btnlog.setOnClickListener(v -> SetProfile());
    }

    private void SetProfile() {
        if (!tvName.getText().toString().isEmpty()) {
            bundle1.putString("nameKey", tvName.getText().toString());
        } else if (tvName.getText().toString().isEmpty()) {
            bundle1.putString("nameKey", "No name setted");
        }
        Intent myIntent6 = new Intent(this, MainActivity2.class);
        myIntent6.putExtra("BUNDLE_KEY2", bundle1);
        startActivity(myIntent6);
    }

}