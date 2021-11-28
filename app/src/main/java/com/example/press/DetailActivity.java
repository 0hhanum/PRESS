package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(DetailActivity.this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        // Intent 정보 수신
        Intent intent = getIntent();
        String exercise = intent.getExtras().getString("exercise");
        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(exercise);

        List<Object> sets = dbHelper.getResultByDate(exercise);
        for (int i=0;i<sets.size();i += 2){
//            String date = String.valueOf(sets.get(i));
//            int total =
            Log.d("testing", String.valueOf(sets.get(i)));
            Log.d("testing", String.valueOf(sets.get(i+1)));
        }
    }
    public void go_to_exercise(View view){
        Intent intent = new Intent(DetailActivity.this, RecordActivity.class);
        intent.putExtra("exercise", getIntent().getExtras().getString("exercise"));
        startActivity(intent);
    }
}