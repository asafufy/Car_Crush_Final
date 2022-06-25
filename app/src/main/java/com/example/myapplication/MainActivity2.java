package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
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

    SharedPreferences sp;

    Dialog d;
    private Bundle bundle1;
    private String nameString;
    private Button btnInfo;
    TextView tv4;
    BroadCastBattery broadCastBattery;


    TextView tv;
    Button btnPush;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bundle1 = getIntent().getBundleExtra("BUNDLE_KEY2");//new
        setContentView(R.layout.activity_main2);//new
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnPush = (Button) findViewById(R.id.btnPush);
        tv4=(TextView)findViewById(R.id.tv4);
        broadCastBattery=new BroadCastBattery();
        sp=getSharedPreferences("details1",0);//new1
        registerForContextMenu(tv);
        initButtons();
        SetUsername();
        Servircebar();
    }

    private void Servircebar() { btnPush.setOnClickListener(v -> NewServicebar());}

    private void NewServicebar() {
        int icon = android.R.drawable.star_on;
        long when = System.currentTimeMillis();
        String title = "Car Crush";
        String ticker = "ticker";
        String text="Your Car Is Ready For Another Ride";
        Intent intent = new Intent(MainActivity2.this, secondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity2.this, 0, intent, 0);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "M_CH_ID");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        Notification notification = builder.setContentIntent(pendingIntent)
                .setSmallIcon(icon).setTicker(ticker).setWhen(when)
                .setAutoCancel(true).setContentTitle(title)
                .setContentText(text).build();
        notificationManager.notify(1, notification);
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
        myIntent4.putExtra("BUNDLE_KEY2", bundle1);//new
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
    private void initButtons() {
        btnInfo.setOnClickListener(v -> newdealog());
    }

    private void newdealog() {
        d= new Dialog(this);
        d.setContentView(R.layout.costum_layout);
        d.setTitle("Login");
        d.setCancelable(true);
        d.show();
    }
    private class BroadCastBattery extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            int battery = intent.getIntExtra("level",0);
            tv4.setText(String.valueOf(battery) + " %");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadCastBattery,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastBattery);
    }
}
