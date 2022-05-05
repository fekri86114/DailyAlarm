package com.app.examplepro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton mAddFab, aboutUsFab;
    TextView aboutUsActionText;
    Boolean isAllFABsVisible;
    private AppCompatImageView startButton, pauseButton, imageView, nextButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        nextButton = findViewById(R.id.next);
        Glide.with(this).load("https://d338t8kmirgyke.cloudfront.net/icons/icon_pngs/000/018/361/original/next_6724238.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(nextButton);
        nextButton.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay == 7 || timeOfDay == 8) {
            Toast.makeText(this, "Good Morning\nLet's start the day!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://c8.alamy.com/comp/GP3MYP/good-morning-GP3MYP.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 7 & timeOfDay < 8) {
            Toast.makeText(this, "It's time for breakfast!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTDLntfoxLw0CTpjgsjdayzgPSnpeU6VAMgjvNqXkRkISVx20YYYujuLf5wtPsdUFtnjzorOIuOeQ3CTwMPJrE").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 11 & timeOfDay < 12) {
            Toast.makeText(this, "It's time for lunch!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcSV1DnLClpctBRWILcGwq7qbRe-MbFi2Vs1VwRkvhp6XKXxXHOacaZQromCQszFQ1ONbZmw_hkcor-e0aObcnQ").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 12 & timeOfDay < 13) {
            Toast.makeText(this, "It's time for School!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://showmeinstitute.org/wp-content/uploads/2019/12/shutterstock_309241295.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            Toast.makeText(this, "Good Afternoon", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://i.pinimg.com/originals/01/a7/ee/01a7ee26d7e2827cc1a7c2fd1f6fbdf6.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            Toast.makeText(this, "Good Evening", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://images.unsplash.com/photo-1586791965591-15d8892f6dd6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZXZlbmluZyUyMHN1bnNldHxlbnwwfHwwfHw%3D&w=1000&q=80").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            Toast.makeText(this, "Good Night", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://cdn.mos.cms.futurecdn.net/whguqi9sNbbgp5uVckgz2K.jpg").
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


        }
    }
}