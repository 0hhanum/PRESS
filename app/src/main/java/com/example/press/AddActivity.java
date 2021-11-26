package com.example.press;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        // Intent 정보 수신
        Intent intent = getIntent();
        String exercise = intent.getExtras().getString("exercise");

//        TextView subtitle = (TextView) findViewById(R.id.subtitle);
//        subtitle.setText(exercise);
    }
}