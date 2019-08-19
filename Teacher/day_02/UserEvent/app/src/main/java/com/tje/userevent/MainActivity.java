package com.tje.userevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    Button btnOutput;
    TextView tvOutputMsg;
    EditText etInputMsg;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        btnOutput = findViewById(R.id.button);
        tvOutputMsg = findViewById(R.id.textView2);
        etInputMsg = findViewById(R.id.editText2);
        */

        /*
        // 버튼 클릭 이벤트 처리
        // setOnClickListener 메소드에 이벤트를 처리할 수 있는 리스너 객체를
        // 전달하여 이벤트를 처리할 수 있음
        //this.btnOutput.setOnClickListener(new View.OnClickListener() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // 사용자가 입력한 메세지의 값을 String 타입으로 반환
                String msg = ((EditText)findViewById(R.id.editText2)).getText().toString();
                // 사용자가 입력한 메세지 삭제
                ((EditText)findViewById(R.id.editText2)).setText("");
                // TextView에 사용자가 입력한 메세지를 설정
                ((TextView)findViewById(R.id.textView2)).setText(msg);
            }
        });
        */
    }

    public void btnClick(View view) {
        // 사용자가 입력한 메세지의 값을 String 타입으로 반환
        String msg = ((EditText)findViewById(R.id.editText2)).getText().toString();
        // 사용자가 입력한 메세지 삭제
        ((EditText)findViewById(R.id.editText2)).setText("");
        // TextView에 사용자가 입력한 메세지를 설정
        ((TextView)findViewById(R.id.textView2)).setText(msg);
    }
}












