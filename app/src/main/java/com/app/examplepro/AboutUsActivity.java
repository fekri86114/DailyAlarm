package com.app.examplepro;

import android.content.Intent;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class AboutUsActivity extends AppCompatActivity {

    private AppCompatTextView proGit, mohamadGit, hastGit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        proGit = findViewById(R.id.project_github);
        proGit.setOnClickListener(view-> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rubikamp/Daily_Alarm.git"))));

        mohamadGit = findViewById(R.id.github_mohammadreza);
        mohamadGit.setOnClickListener(view-> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fekri86114"))));

        hastGit = findViewById(R.id.github_hasti);
        hastGit.setOnClickListener(view-> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Hastimohammadi"))));

    }
}