package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        // Intent 정보 수신
        Intent intent = getIntent();
        String exercise = intent.getExtras().getString("exercise");

        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(exercise);
        //
        Button button = (Button) findViewById(R.id.addSetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordActivity.this, AddActivity.class);
                // intent 로 dialog popup 띄우기
                startActivityForResult(intent, 1);
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                String kg = data.getStringExtra("kg");
                String reps = data.getStringExtra("reps");
                TextView tx = (TextView) findViewById(R.id.record1);
                tx.setText(kg + " kg  " + reps + " 회 ");
                tx.setTextSize(20);
            }
        }
    }
}