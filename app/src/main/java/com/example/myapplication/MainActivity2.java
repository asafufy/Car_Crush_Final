package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {

    private Bundle bundle1;
    private String nameString;


    TextView tv;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bundle1 = getIntent().getBundleExtra("BUNDLE_KEY2");//new
        setContentView(R.layout.activity_main2);//new
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        registerForContextMenu(tv);
        SetUsername();
    }

    private void SetUsername() {
            nameString = bundle1.getString("nameKey");
            tv2.setText(nameString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_login) {
            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
            loginAcitivity();
            return true;
        }
        else
        if (item.getItemId() == R.id.action_register) {
            Toast.makeText(this, "You selected Register", Toast.LENGTH_SHORT).show();
            registerAcitivity();
            return true;
        }
        else
        if (item.getItemId() == R.id.action_HomePage) {
            Toast.makeText(this, "You selected HomePage", Toast.LENGTH_SHORT).show();
            HomePageAcitivity();
            return true;
        }
        return false;
    }

    private void HomePageAcitivity() {
        Intent myIntent4 = new Intent(this, FirstPage.class);//Todo
        startActivity(myIntent4);
    }//Todo

    //    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater infalter = getMenuInflater();
//        infalter.inflate(R.menu.main_menu1, menu);
//    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        // TODO Auto-generated method stub
//        if (item.getItemId() == R.id.action_login) {
//            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
//            loginAcitivity();
//            return true;
//        }
//        else
//            if (item.getItemId() == R.id.action_register) {
//            Toast.makeText(this, "You selected register", Toast.LENGTH_SHORT).show();
//            registerAcitivity();
//            return true;
//        }
//        return false;
//    }

    private void registerAcitivity() {
        Intent myIntent3 = new Intent(this, Register.class);//Todo
        startActivity(myIntent3);
    }//Todo
    private void loginAcitivity() {//Todo
        Intent myIntent2 = new Intent(this, Login.class);//Todo
        startActivity(myIntent2);
    }

}