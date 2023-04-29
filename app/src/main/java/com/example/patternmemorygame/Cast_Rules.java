package com.example.patternmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Cast_Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_rules);
        LinearLayout ll = findViewById(R.id.layout);
        Intent intent = getIntent();
        if(intent.getStringExtra("message").equals("1"))
            ll.setBackgroundResource(R.drawable.cast_background);
        else
            ll.setBackgroundResource(R.drawable.rules_background);

    }
}