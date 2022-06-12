package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Random;

public class MainActivity  extends AppCompatActivity  implements mimshak{


    final int DELAY = 1000;
    private int currentDelay;
    private final int numOfLanes=3;
    private final int numOfObstaclesInLane=4;
    private int obstaclesFlags[];
    private int carsFlags[];
    private int numOfLifes=3;
    private boolean newFlag;
    private final int TICKSCORE = 10;
    private Bundle bundle; //New
    private ImageView panel_IMG_left_arrow;
    private ImageView panel_IMG_right_arrow;
    private ImageView[] booms;
    private ImageView[] roads;
    private ImageView[] obstacles;
    private ImageView[] cars;
    private ImageView[] hearts;
    private int currentScore=0;
    private TextView score;
    private MediaPlayer mpCrush;//New
    private String speedString;




    final Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            Log.d("pttt", "Tick: " + 1);
            for (int i = 0; i < booms.length; i++) {
                if(booms[i].getVisibility()==View.VISIBLE) {
                    booms[i].setVisibility(View.INVISIBLE);
                    cars[i].setVisibility(View.VISIBLE);
                }
            }
            moveObstacles();
            if(newFlag)
                newObstacles();
            else
                newFlag=true;
            checkCrush();
            increaseScore(TICKSCORE);
            handler.postDelayed(this, currentDelay);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bundle = getIntent().getBundleExtra("BUNDLE_KEY");
        setContentView(R.layout.activity_main);


        speedString = bundle.getString("speedKey");

        mpCrush = MediaPlayer.create(this, R.raw.sound_car_crush);
        newFlag=true;
        findViews();
        obstaclesFlags = new int[obstacles.length];
        carsFlags = new int[cars.length];
        Arrays.fill(obstaclesFlags,0);
        Arrays.fill(carsFlags,0);

        panel_IMG_left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftArrowAction();
            }


        });
        panel_IMG_right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightArrowAction();
            }
        });
    }

    public void leftArrowAction() {
        for (int i = 0; i < cars.length; i++) {
            if(cars[i].getVisibility()==View.VISIBLE)
                carsFlags[i]=1;
        }


        for (int i = 1; i < carsFlags.length; i++) {
            if(carsFlags[i]==1){
                cars[i].setVisibility(View.INVISIBLE);
                cars[i-1].setVisibility(View.VISIBLE);
            }
        }
        Arrays.fill(carsFlags,0);
        checkCrush();
    }

    private void increaseScore(int up) {
        currentScore+=up;
        score.setText(currentScore+"m");
    }


    public void rightArrowAction() {
        for (int i = 0; i < cars.length; i++) {
            if(cars[i].getVisibility()==View.VISIBLE)
                carsFlags[i]=1;
        }

        for (int i = 0; i < carsFlags.length-1 ; i++) {
            if(carsFlags[i]==1) {
                cars[i].setVisibility(View.INVISIBLE);
                cars[i + 1].setVisibility(View.VISIBLE);
            }
        }
        Arrays.fill(carsFlags,0);
        checkCrush();
    }

    private void checkCrush() {
        for (int i = 0; i < numOfLanes; i++) {
            if(cars[i].getVisibility()==View.VISIBLE && obstacles[i*numOfObstaclesInLane+numOfLanes].getVisibility()==View.VISIBLE){
                booms[i].setVisibility(View.VISIBLE);
                cars[i].setVisibility(View.INVISIBLE);
                obstacles[i*numOfObstaclesInLane+numOfLanes].setVisibility(View.INVISIBLE);
                Toast.makeText(this, "CRUSH!", Toast.LENGTH_SHORT).show();
                decreseLife();
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                    mpCrush.start();
                }

            }
        }
    }

    private void decreseLife() {
        hearts[numOfLifes-1].setVisibility(View.INVISIBLE);
        numOfLifes--;
        if(numOfLifes<1){
            gameOver();
//            numOfLifes=3;
//            for (int i = 0; i < numOfLifes; i++) {
//                hearts[i].setVisibility(View.VISIBLE);
//            }
        }
    }

    private void gameOver() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("pttt", "speedString= " + speedString);
        if(speedString != null){
            if(speedString.contains("fast"))
                currentDelay = 500;
            else if(speedString.contains("slow"))
                currentDelay =2000;
        }
        else
            currentDelay =1500;
        handler.postDelayed(r, currentDelay);

    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(r);
    }


    private void moveObstacles() {
        for (int i = 0; i < obstacles.length; i++) {
            if(obstacles[i].getVisibility()==View.VISIBLE){
                obstaclesFlags[i]=1;
            }
        }


        for (int i = obstaclesFlags.length-1; i >= 0; i--) {
            if(obstaclesFlags[i]==1) {

                if (i != numOfObstaclesInLane - 1 && (i - numOfObstaclesInLane < 0 || i % numOfObstaclesInLane != 3)) {
                    obstacles[i + 1].setVisibility(View.VISIBLE);
                }
                obstacles[i].setVisibility(View.INVISIBLE);
            }
        }
        Arrays.fill(obstaclesFlags,0);
    }

    private void newObstacles() {
        int random=new Random().nextInt(3);

        obstacles[random*numOfObstaclesInLane].setVisibility(View.VISIBLE);
        newFlag=false;

    }

    private void findViews() {
        score = findViewById(R.id.panel_LBL_score);
        panel_IMG_left_arrow = findViewById(R.id.panel_IMG_left_arrow);
        panel_IMG_right_arrow = findViewById(R.id.panel_IMG_right_arrow);
        roads = new ImageView[]{
                findViewById(R.id.panel_IMG_road1),
                findViewById(R.id.panel_IMG_road2),
                findViewById(R.id.panel_IMG_road3)
        };
        Glide.with(this).load(R.drawable.img_road).into(roads[0]);
        Glide.with(this).load(R.drawable.img_road).into(roads[1]);
        Glide.with(this).load(R.drawable.img_road).into(roads[2]);
        booms = new ImageView[]{
                findViewById(R.id.panel_IMG_boom1),
                findViewById(R.id.panel_IMG_boom2),
                findViewById(R.id.panel_IMG_boom3)
        };
        hearts = new ImageView[]{
                findViewById(R.id.panel_IMG_heart1),
                findViewById(R.id.panel_IMG_heart2),
                findViewById(R.id.panel_IMG_heart3)
        };
        cars = new ImageView[]{
                findViewById(R.id.panel_IMG_car1),
                findViewById(R.id.panel_IMG_car2),
                findViewById(R.id.panel_IMG_car3)
        };
        obstacles= new ImageView[]{
            findViewById(R.id.panel_IMG_obstacle11),
            findViewById(R.id.panel_IMG_obstacle12),
            findViewById(R.id.panel_IMG_obstacle13),
            findViewById(R.id.panel_IMG_obstacle14),//3
            findViewById(R.id.panel_IMG_obstacle21),
            findViewById(R.id.panel_IMG_obstacle22),
            findViewById(R.id.panel_IMG_obstacle23),
            findViewById(R.id.panel_IMG_obstacle24),//7
            findViewById(R.id.panel_IMG_obstacle31),
            findViewById(R.id.panel_IMG_obstacle32),
            findViewById(R.id.panel_IMG_obstacle33),
            findViewById(R.id.panel_IMG_obstacle34),//11

        };
    }
}