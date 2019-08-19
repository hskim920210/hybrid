package com.tje.userevent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textView;



    // 버튼 클릭 이벤트 처리
    // setOnclickListener 메소드에 이벤트를 처리할 수 있는
    // 리스너 객체를 전달하여 이벤트를 처리할 수 있다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자가 입력한 메세지의 값을 String 타입으로 반환
                String result = editText.getText().toString();
                // 사용자가 입력한 메세지 삭제
                editText.setText("");
                // 사용자가 입력한 메세지를 설정
                // CharSequece는 String과 호환이 된다.
                textView.setText(result);
            }
        });
        */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
                showDialog();
            }
        });
    }

    public void btnClick(View view) {
        // 사용자가 입력한 메세지의 값을 String 타입으로 반환
        String result = editText.getText().toString();
        // 사용자가 입력한 메세지 삭제
        editText.setText("");
        // 사용자가 입력한 메세지를 설정
        // CharSequece는 String과 호환이 된다.
        textView.setText(result);
    }


    public void showToast() {
        // 227쪽 오타 Editable로 하자(219 쪽의 내용이랑도 같은문제)
        Editable input = editText.getText();
        textView.setText(input);

        String result = editText.getText().toString();
        Context ct = getApplicationContext();
        int dur = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(ct, result, dur);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void showDialog(){
        Editable input = editText.getText();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(input);
        builder.setTitle("대화상자 타이틀부분");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "확인누룸", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "취소누룸", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
