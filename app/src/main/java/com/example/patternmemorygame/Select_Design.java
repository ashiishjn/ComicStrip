package com.example.patternmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_Design extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_design);
    }
    public void Tap(View v)
    {
        playTapSound();
        Button bt = findViewById(v.getId());
        String tag = bt.getTag().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("message", tag);
        startActivity(intent);
    }
    MediaPlayer media;
    public void playTapSound()
    {
        media= MediaPlayer.create(Select_Design.this, R.raw.tap3);
        media.start();
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
    }
}