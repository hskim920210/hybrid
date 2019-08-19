package com.tje.threadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnThreadStart;

    int i = 1;

    class NormalThread extends Thread {
        @Override
        public void run() {
            // 쓰레드의 run 메소드가 해야할 작업.....

            for( i = 1 ; i <= 10 ; i++ ) {
            // 메인쓰레드에게 처리할 UI 작업을 전달하는 코드
                runOnUiThread(new Runnable() {
                @Override
                    public void run() {
                        textView.setText(
                            String.format("Thread MSG-%d", i));
                    }
                });

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnThreadStart = findViewById(R.id.btnThreadStart);

        btnThreadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NormalThread t = new NormalThread();
                t.start();

                /*
                new Thread() {
                    @Override
                    public void run() {
                        for( int i = 1 ; i <= 10 ; i++ ) {
                            textView.setText(
                                    String.format("Thread MSG-%d", i));

                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                */
            }
        });
    }
}










