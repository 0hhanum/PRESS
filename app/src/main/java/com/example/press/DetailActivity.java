package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        // Intent 정보 수신
        Intent intent = getIntent();
        String exercise = intent.getExtras().getString("exercise");

        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(exercise);
    }

    public void go_to_exercise(View view){
        Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
        intent.putExtra("exercise", exercise);
        startActivity(intent);
        }
}