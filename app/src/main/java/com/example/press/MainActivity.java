package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        switch (view.getId()){
            case R.id.record1:
                break;
            case R.id.record2:
                break;
            case R.id.record3:
                break;
            case R.id.record4:
                break;
        }
    }

}