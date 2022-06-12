package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FirstPage extends AppCompatActivity{

    private Button btn20;
    private Button buttonPlay;
    private Button profile;
    private RadioButton[] radioButtons;
    private RadioGroup rg;
    private Bundle bundle;
    private Bundle bundle1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        finedViews();
        bundle = new Bundle();
        bundle1 = new Bundle();
        btn20=(Button)findViewById(R.id.btn20);
        buttonPlay=(Button)findViewById(R.id.start_BTN_play);
        profile=(Button)findViewById(R.id.profile);
        initButtons();
        newmassage();
        setProfile();
        //  setBundleusername();



        rg.setOnCheckedChangeListener((group, checkedId) ->{
            RadioButton rb = (RadioButton) findViewById(checkedId);
            String selectedText = (String) rb.getText();
            Log.d("pttt", "selected is: " + selectedText);

            if (selectedText.contains("fast"))
                bundle.putString("speedKey", "fast");
            else if (selectedText.contains("slow"))
                bundle.putString("speedKey", "slow");
            else
                bundle.putString("speedKey", "normal");
        });

    }



    public void onClick() {
        String phoneNumber = "0583758001";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", "you have made contact with Car Crush");
        startActivity(intent);
    }
    private void initButtons() {
        buttonPlay.setOnClickListener(v -> myStartAcitivity());
    }
    private void newmassage() {
        btn20.setOnClickListener(v -> onClick());
    }
    private void setProfile() {
        profile.setOnClickListener(v -> goprofile());
    }

    private void myStartAcitivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("BUNDLE_KEY", bundle);
        startActivity(myIntent);
    }
    private void goprofile() {
        if(bundle1.isEmpty()){
            bundle1.putString("nameKey","No name setted");
        }
        Intent myIntent7 = new Intent(this, MainActivity2.class);
        myIntent7.putExtra("BUNDLE_KEY2", bundle1);
        startActivity(myIntent7);
        //        Intent myIntent1 = new Intent(this, MainActivity2.class);//Todo
//        myIntent1.putExtra("BUNDLE_KEY", bundle);
//        startActivity(myIntent1);
    }
//    public void speedtest() {
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.settings_RAD_group);
//        int radioButtonID = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
//
//
//
//
//    }
    private void finedViews() {
        rg = findViewById(R.id.settings_RAD_group);
    }






}