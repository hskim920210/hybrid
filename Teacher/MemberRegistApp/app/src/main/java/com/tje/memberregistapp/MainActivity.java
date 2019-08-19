package com.tje.memberregistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;

    // 선택된 라디오 버튼의 값을 추출할 수 있는 변수 선언
    RadioGroup rg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = findViewById(R.id.submitBtn);
        rg = findViewById(R.id.genderRadionGroup);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 선택된 라디오 버튼의 값을 추출
                // 1. 라디오 그룹에서 현재 선택된 라디오 버튼의
                // ID 값을 추출
                int selectedRadioId = rg.getCheckedRadioButtonId();
                // 2. 추출된 ID 값을 사용하여 라디오 버튼의 객체를 반환
                RadioButton gender = findViewById(selectedRadioId);

                Toast.makeText(
                        getApplicationContext(), gender.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}










