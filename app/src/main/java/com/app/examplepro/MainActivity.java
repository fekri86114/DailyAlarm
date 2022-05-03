package com.app.examplepro;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatImageView startButton, pauseButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.buray);

        startButton = findViewById(R.id.start);
        Glide.with(this).load("https://cdn0.iconfinder.com/data/icons/computer-process-outline/64/start_new_beginning_go-512.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(startButton);
        startButton.setOnClickListener(this);

        pauseButton = findViewById(R.id.pause);
        Glide.with(this).load("https://cdn-icons-png.flaticon.com/512/1878/1878931.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(pauseButton);
        pauseButton.setOnClickListener(this);


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            Toast.makeText(this, "Good Morning", Toast.LENGTH_LONG).show();
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            Toast.makeText(this, "Good Afternoon", Toast.LENGTH_LONG).show();
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            Toast.makeText(this, "Good Evening", Toast.LENGTH_LONG).show();
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            Toast.makeText(this, "Good Night", Toast.LENGTH_LONG).show();
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
        }
    }
}