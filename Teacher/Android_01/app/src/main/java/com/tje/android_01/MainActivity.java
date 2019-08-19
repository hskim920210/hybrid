package com.tje.android_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int count = 1;

    // UI에 정의된 각 위젯에 접근하기 위한
    // 클래스의 변수 선언
    TextView titleTextView;
    Button changeTitleBtn;
    Button toastTestBtn;
    Button closeBtn;
    Button visibilityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = (TextView)findViewById(R.id.titleTextView);
        changeTitleBtn = (Button)findViewById(R.id.udpateTitleBtn);
        toastTestBtn = (Button)findViewById(R.id.toastTestBtn);
        closeBtn = (Button)findViewById(R.id.closeBtn);
        visibilityBtn = (Button)findViewById(R.id.visibilityBtn);

        changeTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTextView.setText("타이틀 - " + count);
                count++;
            }
        });

        toastTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getApplicationContext(),
                        titleTextView.getText(), Toast.LENGTH_LONG).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                Process.killProcess(Process.myPid());
            }
        });

        visibilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( titleTextView.getVisibility() == View.VISIBLE )
                    titleTextView.setVisibility(View.INVISIBLE);
                else if(titleTextView.getVisibility() == View.INVISIBLE )
                    titleTextView.setVisibility(View.GONE);
                else if( titleTextView.getVisibility() == View.GONE )
                    titleTextView.setVisibility(View.VISIBLE);
            }
        });
    }
}









