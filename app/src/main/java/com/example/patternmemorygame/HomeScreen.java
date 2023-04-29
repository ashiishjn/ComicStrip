package com.example.patternmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        playMusic(R.raw.background_music);
    }
    public void play(View v)
    {
        playTapSound();
        Intent intent = new Intent(HomeScreen.this, Select_Design.class);
        startActivity(intent);
    }
    public void cast(View v)
    {
        playTapSound();
        Intent intent = new Intent(this, Cast_Rules.class);
        intent.putExtra("message", "1");
        startActivity(intent);
    }
    public void rules(View v)
    {
        playTapSound();
        Intent intent = new Intent(this, Cast_Rules.class);
        intent.putExtra("message", "2");
        startActivity(intent);
    }
    public static MediaPlayer music;
    public void playMusic(int id)
    {
        music = MediaPlayer.create(HomeScreen.this, id);
        music.setLooping(true);
        music.start();
    }
    MediaPlayer media;
    public void playTapSound()
    {
        media= MediaPlayer.create(HomeScreen.this, R.raw.tap2);
        media.start();
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
    }
}