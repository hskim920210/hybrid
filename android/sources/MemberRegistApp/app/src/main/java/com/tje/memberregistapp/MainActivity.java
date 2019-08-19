package com.tje.memberregistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText idEditText;
    EditText pwEditText;
    EditText nameEditText;
    EditText ageEditText;
    EditText telEditText;
    EditText addressEditText;

    // 선택된 라디오 버튼의 값을 추출할 수 있는 변수 선언
    RadioGroup radioGroup;

    CheckBox c1, c2, c3, c4, c5, c6;

    ToggleButton liBtn;
    Switch liSwitch;

    Button submitBtn;
    Button resetBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEditText = findViewById(R.id.idEditText);
        pwEditText = findViewById(R.id.pwEditText);
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        telEditText = findViewById(R.id.telEditText);
        addressEditText = findViewById(R.id.addressEditText);

        c1 = findViewById(R.id.hobby_1CheckBox);
        c2 = findViewById(R.id.hobby_2CheckBox);
        c3 = findViewById(R.id.hobby_3CheckBox);
        c4 = findViewById(R.id.hobby_4CheckBox);
        c5 = findViewById(R.id.hobby_5CheckBox);
        c6 = findViewById(R.id.hobby_6CheckBox);

        radioGroup = findViewById(R.id.radioGroup);

        resetBtn = findViewById(R.id.resetButton);
        submitBtn = findViewById(R.id.submitButton);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEditText.setText("");
                pwEditText.setText("");
                nameEditText.setText("");
                ageEditText.setText("");
                telEditText.setText("");
                addressEditText.setText("");
                radioGroup.check(0);
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);
                c6.setChecked(false);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String tel = telEditText.getText().toString();
                String addr = addressEditText.getText().toString();
                // 선택된 라디오버튼의 값을 추출
                // 라디오 그룹에서 현재 선택된 라디오 버튼의
                // ID 값을 추출
                // int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                // 추출된 ID값으로 라디오버튼의 객체 반환
                RadioButton selected = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String gender = selected.getText().toString();
                Toast.makeText(getApplicationContext(), "id : " +id+
                        ", pw : "+pw+", name : "+name+", age : "+age+", tel : "+tel+", addr : "+addr+
                        ", gender : "+gender, Toast.LENGTH_LONG ).show();
            }
        });


    }
}
