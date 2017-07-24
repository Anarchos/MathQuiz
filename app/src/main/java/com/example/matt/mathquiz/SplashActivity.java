package com.example.matt.mathquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void runEasy(View view)
    {
        MainActivity.Difficulty difficulty = MainActivity.Difficulty.EASY;
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        mainIntent.putExtra("Difficulty", difficulty);
        startActivity(mainIntent);
        finish();
    }
    public void runMed(View view)
    {
        MainActivity.Difficulty difficulty = MainActivity.Difficulty.MEDIUM;
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        mainIntent.putExtra("Difficulty", difficulty);
        startActivity(mainIntent);
        finish();
    }
    public void runHard(View view)
    {
        MainActivity.Difficulty difficulty = MainActivity.Difficulty.HARD;
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        mainIntent.putExtra("Difficulty", difficulty);
        startActivity(mainIntent);
        finish();
    }
}
