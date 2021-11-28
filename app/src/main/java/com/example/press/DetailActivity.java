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
    int lastDayVolume;
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
        // 일자 별 총 볼륨을 받아옴.

        for (int i=0;i<sets.size();i += 2){
            String date = String.valueOf(sets.get(i));
            String volume = String.valueOf(sets.get(i+1));

            LinearLayout linearLayout = findViewById(R.id.setsByDate);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView totalVolume = new TextView(this);
            totalVolume.setText(date + " |  총 " + volume + " kg");
            totalVolume.setTextSize(20);
            params.bottomMargin = 15;
            totalVolume.setLayoutParams(params);
            totalVolume.setGravity(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(totalVolume);
        }
        // 오늘 날짜 구하기
        long now = System.currentTimeMillis();
        Date getDate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(getDate);

        if (String.valueOf(sets.get(sets.size() - 2)).equals(today)) {
            // 만약 오늘 운동 정보가 기록되어 있다면 그 전 정보를 보낸다.
            try {
                lastDayVolume = (int) sets.get(sets.size() - 3);
                // 이전 운동 기록이 있으면 보내고 아니면 0 을 보냄.
            } catch (Exception e) {
                lastDayVolume = 0;
            }
        } else {
            try {
                lastDayVolume = (int) sets.get(sets.size() - 1);
                // 오늘 운동 기록이 없으면 DB 상 마지막 데이터를 보냄.
                // 그것도 없으면 0 을 보냄.
            } catch (Exception e) {
                lastDayVolume = 0;
            }
        }
    }
    public void go_to_exercise(View view){
        Intent intent = new Intent(DetailActivity.this, RecordActivity.class);
        intent.putExtra("exercise", getIntent().getExtras().getString("exercise"));
        intent.putExtra("lastVolume", lastDayVolume);
        startActivity(intent);
    }
}