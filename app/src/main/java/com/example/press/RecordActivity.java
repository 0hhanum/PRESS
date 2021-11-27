package com.example.press;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

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
                ScrollView setList = (ScrollView) findViewById(R.id.sets);
                String kg = data.getStringExtra("kg");
                String reps = data.getStringExtra("reps");
                TextView tx = (TextView) findViewById(R.id.record1);
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
            }
        }
    }
}
