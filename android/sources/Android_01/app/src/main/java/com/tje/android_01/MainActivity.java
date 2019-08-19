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

    // UI에 정의된 각 위젯에  접근하기 위한 클래스의 변수선언
    // android.widget 패키지에 있는 TextView, Button으로 변수를 만들 수 있다.
    // 이건 layout에서 드래그앤드롭으로 한 이름과 똑같이 사용.
    TextView titleTextView;
    Button changeTitleBtn;
    Button toastTestBtn;
    Button closeBtn;
    Button visibilityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // R은 res폴더를 뜻하고 layout. 하면 layout 폴더에 접근
        // 그 내부의 activity_main.xml을 하기위해
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        // changeTitleBtn = findViewById() : 화면에 나온 모든것을 id로 찾아 접근가능
        titleTextView = (TextView)findViewById(R.id.titleTextView);
        changeTitleBtn = (Button)findViewById(R.id.updateTitleBtn);
        toastTestBtn = (Button)findViewById(R.id.toastTestBtn);
        closeBtn = (Button)findViewById(R.id.closeBtn);
        visibilityBtn = (Button)findViewById(R.id.visibilityBtn);

        changeTitleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                titleTextView.setText("타이틀 - " + count);
                count++;
            }
        });

        toastTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast는 안드로이드에서 팝업창이 나오는것.
                // 팝업창을 띄울 메인 윈도우를 getApplicationContext : 현재 액티비티 창에 띄울거라는 셋팅
                // 잠깐 보고 사라질거면 short
                Toast.makeText(getApplicationContext(), titleTextView.getText(), Toast.LENGTH_LONG).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // APP을 종료하는 메소드
                // finish();
                // 완전히 끝내는 메소드
                Process.killProcess(Process.myPid());
            }
        });

        visibilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현재 보이는지 상태를 가져온다.
                if( titleTextView.getVisibility() == View.VISIBLE ) {
                    // 영역은 살고 텍스트만 사라짐
                    titleTextView.setVisibility(View.INVISIBLE);
                } else if( titleTextView.getVisibility() == View.INVISIBLE ) {
                    // 영역과 텍스트 모두 사라짐
                    titleTextView.setVisibility(View.GONE);
                } else if( titleTextView.getVisibility() == View.GONE ) {
                    titleTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
