package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText tvName1;
    private Button btnlog2;
    private Bundle bundle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvName1 = (EditText) findViewById(R.id.txtEmail);
        btnlog2 = (Button) findViewById(R.id.btnLogin);
        bundle1 = new Bundle();
        LogingIn();
    }
    private void LogingIn() {
        btnlog2.setOnClickListener(v -> SetProfile1());
    }

    private void SetProfile1() {
        if (!tvName1.getText().toString().isEmpty()) {
            bundle1.putString("nameKey", tvName1.getText().toString());
        } else if (tvName1.getText().toString().isEmpty()) {
            bundle1.putString("nameKey", "No name setted");
        }
        Intent myIntent6 = new Intent(this, MainActivity2.class);
        myIntent6.putExtra("BUNDLE_KEY2", bundle1);
        startActivity(myIntent6);
    }
}