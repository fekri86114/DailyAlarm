package com.app.examplepro;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton mAddFab, aboutUsFab;
    private TextView aboutUsActionText;
    private Boolean isAllFABsVisible;
    private AppCompatImageView startButton, pauseButton, imageView;
    private MediaPlayer mediaPlayer;
    private AppCompatButton buttonStartServiceBack, buttonStopServiceBack, startServiceFor, stopServiceFor;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!foregroundServiceRunning()) {
            Intent serviceIntent = new Intent(this,
                    MyForegroundService.class);
            startForegroundService(serviceIntent);
        }

        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        startForegroundService(serviceIntent);

        buttonStartServiceBack = findViewById(R.id.start_service_back);
        buttonStartServiceBack.setOnClickListener(this);

        buttonStopServiceBack = findViewById(R.id.stop_service_back);
        buttonStopServiceBack.setOnClickListener(this);

        startServiceFor = findViewById(R.id.start_service_for);
        startServiceFor.setOnClickListener(this);

        stopServiceFor = findViewById(R.id.stop_service_for);
        stopServiceFor.setOnClickListener(this);


        mAddFab = findViewById(R.id.add_fab);
        aboutUsFab = findViewById(R.id.about_us);
        aboutUsActionText = findViewById(R.id.about_us_textview);

        aboutUsFab.setVisibility(View.GONE);
        aboutUsActionText.setVisibility(View.GONE);

        isAllFABsVisible = false;

        mAddFab.setOnClickListener(
                view -> {
                    if (!isAllFABsVisible) {

                        aboutUsFab.show();
                        aboutUsActionText.setVisibility(View.VISIBLE);

                    } else {
                        aboutUsFab.hide();
                        aboutUsActionText.setVisibility(View.GONE);

                        isAllFABsVisible = false;
                    }
                });
        aboutUsFab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutUsActivity.class)));


        imageView = findViewById(R.id.image);
        mediaPlayer = MediaPlayer.create(this, R.raw.buray);

        startButton = findViewById(R.id.start);
        Glide.with(this).load("https://cdn0.iconfinder.com/data/icons/computer-process-outline/64/start_new_beginning_go-512.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(startButton);
        startButton.setOnClickListener(this);

        pauseButton = findViewById(R.id.pause);
        Glide.with(this).load("https://cdn-icons-png.flaticon.com/512/1878/1878931.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(pauseButton);
        pauseButton.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay == 7 && timeOfDay == 8) {
            Toast.makeText(this, "Good Morning\nLet's start the day!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://c8.alamy.com/comp/GP3MYP/good-morning-GP3MYP.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 7 && timeOfDay < 8) {
            Toast.makeText(this, "It's time for breakfast!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTDLntfoxLw0CTpjgsjdayzgPSnpeU6VAMgjvNqXkRkISVx20YYYujuLf5wtPsdUFtnjzorOIuOeQ3CTwMPJrE").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 11 && timeOfDay < 12) {
            Toast.makeText(this, "It's time for lunch!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcSV1DnLClpctBRWILcGwq7qbRe-MbFi2Vs1VwRkvhp6XKXxXHOacaZQromCQszFQ1ONbZmw_hkcor-e0aObcnQ").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 12 && timeOfDay < 13) {
            Toast.makeText(this, "It's time for School!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://showmeinstitute.org/wp-content/uploads/2019/12/shutterstock_309241295.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            Toast.makeText(this, "Good Afternoon", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://i.pinimg.com/originals/01/a7/ee/01a7ee26d7e2827cc1a7c2fd1f6fbdf6.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mediaPlayer.start();
                break;
            case R.id.pause:
                mediaPlayer.pause();
                break;
            case R.id.start_service_back:
                Intent serviceIntent = new Intent(this,
                        MyBackgroundService.class);
                startService(serviceIntent);
                break;
            case R.id.stop_service_back:
                Intent intent = new Intent(this,
                        MyBackgroundService.class);
                stopService(intent);
                break;
            case R.id.start_service_for:
                Intent intentStart = new Intent(this,
                        MyForegroundService.class);
                startService(intentStart);
                break;
            case R.id.stop_service_for:
                Intent intentStop = new Intent(this,
                        MyForegroundService.class);
                stopService(intentStop);
        }
    }

    public boolean foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (MyForegroundService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
