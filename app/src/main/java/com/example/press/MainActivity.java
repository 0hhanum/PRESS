package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] breast_exercises = {
            "벤치 프레스",
            "덤벨 프레스",
            "체스트 프레스",
            "인클라인 벤치 프레스",
            "딥스",
            "플라이",
            "중량 푸쉬업",
    };
    String[] back_exercises = {
            "풀업",
            "바벨 로우",
            "덤벨 로우",
            "펜듈레이 로우",
            "데드리프트",
            "시티드 로우",
            "랫풀다운",
            "케이블 로우"
    };
    String[] shoulder_exercises = {
            "사이드 레터럴 레이즈",
            "숄더 프레스",
            "밀리터리 프레스",
            "오버헤드 프레스",
            "덤벨 프레스",
            "비하인드 프레스",
    };
    String[] leg_exercises = {
            "스쿼트",
            "레그 익스텐션",
            "레그 컬",
            "런지",
            "힙 쓰러스트",
            "레그 프레스",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
    }

    public void on_button_click(View view){
        LinearLayout lists = (LinearLayout) findViewById(R.id.exercise_list);
        switch (view.getId()){
            case R.id.breast:
                lists.removeAllViews();
                for (String exercise : breast_exercises){
                    TextView tx = new TextView(this);
                    tx.setText(exercise);
                    tx.setHeight(80);
                    tx.setGravity(Gravity.CENTER);
                    tx.setTextSize(20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.bottomMargin = 100;
                    tx.setLayoutParams(params);
                    tx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Intent 이용해 선택한 운동을 보낸다
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("exercise", exercise);
                            startActivity(intent);
                        }
                    });
                    lists.addView(tx);
                }
                break;
            case R.id.back:
                lists.removeAllViews();
                for (String exercise : back_exercises){
                    TextView tx = new TextView(this);
                    tx.setText(exercise);
                    tx.setHeight(80);
                    tx.setGravity(Gravity.CENTER);
                    tx.setTextSize(20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.bottomMargin = 100;
                    tx.setLayoutParams(params);
                    lists.addView(tx);
                }
                break;
            case R.id.shoulder:
                lists.removeAllViews();
                for (String exercise : shoulder_exercises){
                    TextView tx = new TextView(this);
                    tx.setText(exercise);
                    tx.setHeight(80);
                    tx.setGravity(Gravity.CENTER);
                    tx.setTextSize(20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.bottomMargin = 100;
                    tx.setLayoutParams(params);
                    lists.addView(tx);
                }
                break;
            case R.id.leg:
                lists.removeAllViews();
                for (String exercise : leg_exercises){
                    TextView tx = new TextView(this);
                    tx.setText(exercise);
                    tx.setHeight(80);
                    tx.setGravity(Gravity.CENTER);
                    tx.setTextSize(20);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.bottomMargin = 100;
                    tx.setLayoutParams(params);
                    lists.addView(tx);
                }
                break;
        }
    }

}