package com.example.patternmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int levelnumber = 1;
    int totalTaps = 2;
    List<Integer> l = new ArrayList<>();
    List<Integer> pattern = new ArrayList<>();
    boolean gameActive = false;
    int tapCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemstoList();
        Intent intent = getIntent();
        createDesign(intent.getStringExtra("message"));
        TextView tv = findViewById(R.id.level_number);
        tv.setText("1");
        tv = findViewById(R.id.Total_Taps);
        tv.setText("2");
        generatepattern();
    }

    public void addItemstoList()
    {
        for(int i=0;i<34;i++)
            pattern.add(i);
        pattern.remove(24);
        pattern.remove(14);
        pattern.remove(4);
    }

    public void generatepattern()
    {
        Collections.shuffle(pattern);
        GridLayout grid = findViewById(R.id.grid);
        for(int i=0;i<totalTaps;i++)
        {
            ImageView img = (ImageView) grid.getChildAt(pattern.get(i));
            Animation animate = AnimationUtils.loadAnimation(this, R.anim.blink);
            img.startAnimation(animate);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameActive = true;
                }
            }, 2000);
        }
    }

    MediaPlayer media;
    public void playTapSound()
    {
        media= MediaPlayer.create(MainActivity.this, R.raw.tap2);
        media.start();
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
    }


    public void tapIn(View v)
    {
        ImageView img = (ImageView) findViewById(v.getId());
        int tag = Integer.parseInt(img.getTag().toString());
        if(gameActive && !l.contains(tag))
        {
            playTapSound();
            l.add(tag);
            img.setImageAlpha(0);
            tapCounter++;
        }
        if(tapCounter == totalTaps)
        {
            gameActive = false;
            check();
        }
    }

    public void check()
    {
        Collections.sort(l);
        List<Integer> temp = new ArrayList<>();
        temp = pattern.subList(0,totalTaps);
        Collections.sort(temp);
        TextView result = findViewById(R.id.display_result);
        Button btn = findViewById(R.id.nextlevel_retry_button);
        if(temp.equals(l))
        {
            result.setText("Perfect!");
            btn.setText("Next Level");
        }
        else
        {
            result.setText("Pattern did not match.");
            btn.setText("Retry");
        }
        btn.setVisibility(View.VISIBLE);
    }

    public void reset()
    {
        l.clear();
        tapCounter=0;
        GridLayout grid = findViewById(R.id.grid);
        for(int i=0;i<34;i++)
        {
            ImageView img =(ImageView) grid.getChildAt(i);
            img.setImageAlpha(255);
        }
        TextView result = findViewById(R.id.display_result);
        result.setText("");
    }

    public void buttonCLick(View v)
    {
        media= MediaPlayer.create(MainActivity.this, R.raw.tap3);
        media.start();
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        reset();
        Button button = findViewById(R.id.nextlevel_retry_button);
        if(button.getText().toString().equals("Next Level"))
        {
            if(totalTaps <= 15)
                totalTaps++;
            levelnumber++;
            TextView tv = findViewById(R.id.level_number);
            tv.setText(Integer.toString(levelnumber));
            tv = findViewById(R.id.Total_Taps);
            tv.setText(Integer.toString(totalTaps));
        }
        button.setVisibility(View.INVISIBLE);
        generatepattern();
    }

    public void createDesign(String n)
    {
        LinearLayout ll = findViewById(R.id.gameLayout);
        TextView heading;
        GridLayout grid = findViewById(R.id.grid);
        ImageView img;
        switch (Integer.parseInt(n))
        {
            case 2:
                heading = findViewById(R.id.heading);
                heading.setText("EARTH");
                heading.setTextColor(getResources().getColor(R.color.earth));
                for(int i=0;i<pattern.size();i++)
                {
                    img = (ImageView) grid.getChildAt(pattern.get(i));
                    img.setImageResource(R.drawable.brick_1);
                }
                img = (ImageView) grid.getChildAt(6);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(7);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(8);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(11);
                img.setImageResource(R.drawable.brick_light_green);
                img = (ImageView) grid.getChildAt(12);
                img.setImageResource(R.drawable.brick_light_green);
                img = (ImageView) grid.getChildAt(16);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(17);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(18);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(21);
                img.setImageResource(R.drawable.brick_light_green);
                img = (ImageView) grid.getChildAt(22);
                img.setImageResource(R.drawable.brick_light_green);
                img = (ImageView) grid.getChildAt(26);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(27);
                img.setImageResource(R.drawable.brick_light_blue);
                img = (ImageView) grid.getChildAt(28);
                img.setImageResource(R.drawable.brick_light_blue);
                break;
            case 3:
                heading = findViewById(R.id.heading);
                heading.setText("MARS");
                heading.setTextColor(getResources().getColor(R.color.mars));
                for(int i=0;i<pattern.size();i++)
                {
                    img = (ImageView) grid.getChildAt(pattern.get(i));
                    img.setImageResource(R.drawable.brick_light);
                }
                img = (ImageView) grid.getChildAt(6);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(7);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(8);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(11);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(12);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(16);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(17);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(18);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(21);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(22);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(26);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(27);
                img.setImageResource(R.drawable.brick_dark_yellow);
                img = (ImageView) grid.getChildAt(28);
                img.setImageResource(R.drawable.brick_dark_yellow);
                break;
            case 4:
                heading = findViewById(R.id.heading);
                heading.setText("URANUS");
                heading.setTextColor(getResources().getColor(R.color.uranus));
                for(int i=0;i<pattern.size();i++)
                {
                    img = (ImageView) grid.getChildAt(pattern.get(i));
                    img.setImageResource(R.drawable.brick_purple);
                }
                img = (ImageView) grid.getChildAt(6);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(7);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(8);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(11);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(12);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(16);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(17);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(18);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(21);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(22);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(26);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(27);
                img.setImageResource(R.drawable.brick_blue);
                img = (ImageView) grid.getChildAt(28);
                img.setImageResource(R.drawable.brick_blue);
                break;
            case 5:
                heading = findViewById(R.id.heading);
                heading.setText("SATURN");
                heading.setTextColor(getResources().getColor(R.color.saturn));
                ll.setBackgroundResource(R.drawable.background_black);
                for(int i=0;i<pattern.size();i++)
                {
                    img = (ImageView) grid.getChildAt(pattern.get(i));
                    img.setImageResource(R.drawable.brick_white);
                }
                TextView tv = findViewById(R.id.Total_Taps);
                tv.setTextColor(Color.WHITE);
                tv = findViewById(R.id.Total_taps_text);
                tv.setTextColor(Color.WHITE);
                tv = findViewById(R.id.level_number);
                tv.setTextColor(Color.WHITE);
                tv = findViewById(R.id.level_number_text);
                tv.setTextColor(Color.WHITE);
                tv = findViewById(R.id.heading);
                tv.setTextColor(Color.WHITE);
                tv = findViewById(R.id.display_result);
                tv.setTextColor(Color.WHITE);
                Button bt = findViewById(R.id.nextlevel_retry_button);
                bt.setBackgroundResource(R.drawable.button_design2);
                bt.setTextColor(Color.WHITE);
                break;
            case 6:
                heading = findViewById(R.id.heading);
                heading.setText("NEPTUNE");
                heading.setTextColor(getResources().getColor(R.color.neptune));
                for(int i=0;i<pattern.size();i++)
                {
                    img = (ImageView) grid.getChildAt(pattern.get(i));
                    img.setImageResource(R.drawable.brick_1);
                }
                img = (ImageView) grid.getChildAt(26);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(28);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(31);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(32);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(6);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(8);
                img.setImageResource(R.drawable.brick_skin);
                img = (ImageView) grid.getChildAt(17);
                img.setImageResource(R.drawable.brick_skin);
                break;
        }
    }
}