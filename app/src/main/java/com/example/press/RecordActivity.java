package com.example.press;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecordActivity extends AppCompatActivity {
    String ex;
    DBHelper dbHelper = new DBHelper(RecordActivity.this, 1);
    List<Integer> totalSets = new ArrayList<Integer>();
    int total; int lastVolume;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        // Intent 정보 수신
        Intent intent = getIntent();
        String exercise = intent.getExtras().getString("exercise");
        int lastVolume = intent.getExtras().getInt("lastVolume");
        this.lastVolume = lastVolume;
        ex = exercise;
        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(exercise);
        Button button = (Button) findViewById(R.id.addSetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordActivity.this, AddActivity.class);
                // intent 로 dialog popup 띄우기
                startActivityForResult(intent, 1);

            }
        });

        long now = System.currentTimeMillis();
        Date getDate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(getDate); // 날짜 기록을 위한 오늘 날짜.

        List<Integer> sets = dbHelper.getResult(date, exercise); // db 에 기록된 오늘의 해당 운동 세트 얻어오기
        LinearLayout setList = (LinearLayout) findViewById(R.id.setList);
        // 저장된 운동 렌더링
        Log.d("logging", exercise + " : " + sets.size());
        for (int i=0;i<sets.size();i += 2){
            String kg = Integer.toString(sets.get(i));
            String reps = Integer.toString(sets.get(i+1));

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            linearLayout.setLayoutParams(params);
            TextView newSet = new TextView(this);
            newSet.setText(kg + " kg  " + reps + " 회 ");
            newSet.setTextSize(20);
            params.bottomMargin = 15;
            newSet.setLayoutParams(params);
            newSet.setGravity(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(newSet);
            CheckBox checkBox = new CheckBox(this);
            linearLayout.addView(checkBox);
            setList.addView(linearLayout);
            totalSets.add(Integer.parseInt(kg) * Integer.parseInt(reps));
        }
        total = totalSets.stream().mapToInt(Integer::intValue).sum();
        TextView volume_of_today = findViewById(R.id.volume_of_today);
        volume_of_today.setText("오늘 총 볼륨 " + total + " kg");
        CompareWithLastVolume(lastVolume, total);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                // popup 에서 받아온 data 를 처리
                // 화면에 렌더링 & DB 에 저장

                // 렌더링
                LinearLayout setList = (LinearLayout) findViewById(R.id.setList);
                String kg = data.getStringExtra("kg");
                String reps = data.getStringExtra("reps");

                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                linearLayout.setLayoutParams(params);
                TextView newSet = new TextView(this);
                newSet.setText(kg + " kg  " + reps + " 회 ");
                newSet.setTextSize(20);
                params.bottomMargin = 15;
                newSet.setLayoutParams(params);
                newSet.setGravity(View.TEXT_ALIGNMENT_CENTER);
                linearLayout.addView(newSet);
                CheckBox checkBox = new CheckBox(this);
                linearLayout.addView(checkBox);
                setList.addView(linearLayout);

                // DB 저장
                dbHelper.insert(dbHelper.getLastIndex(ex), ex, Integer.parseInt(kg), Integer.parseInt(reps));
                // 총 볼륨 Update
                total += Integer.parseInt(kg) * Integer.parseInt(reps);
                TextView volume_of_today = findViewById(R.id.volume_of_today);
                volume_of_today.setText("오늘 총 볼륨 " + total + " kg");
                CompareWithLastVolume(lastVolume, total);
            }
        }
    }
    public void CompareWithLastVolume(int lastVolume, int todayVolume){
        TextView last_volume = findViewById(R.id.last_volume);
        int diffrence = todayVolume - lastVolume;
        if (diffrence >= 0){
            last_volume.setText("지난 번보다 +" + diffrence + " kg");
            last_volume.setTextColor(ContextCompat.getColor(this, R.color.blue));
        } else {
            last_volume.setText("지난 번보다 " + diffrence + " kg");
            last_volume.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }
}
