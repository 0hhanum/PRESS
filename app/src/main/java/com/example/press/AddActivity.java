package com.example.press;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add);

        EditText kg = (EditText) findViewById(R.id.kg);
        EditText reps = (EditText) findViewById(R.id.reps);
        Button apply = (Button) findViewById(R.id.addApply);
        Button cancel = (Button) findViewById(R.id.addCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("kg", kg.getText());
                intent.putExtra("reps", reps.getText());
                finish();
            }
        });
    }
}