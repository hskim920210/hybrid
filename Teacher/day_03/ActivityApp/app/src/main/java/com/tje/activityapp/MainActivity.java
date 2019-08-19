package com.tje.activityapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_main;
    EditText editText_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main = findViewById(R.id.btn_main);
        editText_main = findViewById(R.id.editText_main);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity 메소드를 사용한 화면 전환
                // - 데이터 전달은 하지않음
                // startActivity(new Intent(MainActivity.this, SubActivity.class));

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                // 전환되는 액티비티에서 참조할 수 있는 데이터 저장
                // - putExtra 메소드를 사용하여 값을 저장할 수 있음
                intent.putExtra("msg", editText_main.getText().toString());

                //startActivity(intent);
                startActivityForResult(intent, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == 10 ) {
            if( resultCode == Activity.RESULT_OK ) {
                String result = data.getStringExtra("result");
                Toast.makeText(
                        getApplicationContext(),
                        result,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "요청처리에 실패했습니다.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}








