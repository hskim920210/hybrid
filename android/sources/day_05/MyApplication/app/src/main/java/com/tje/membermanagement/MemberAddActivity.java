package com.tje.membermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemberAddActivity extends AppCompatActivity {

    MemberDbHelper memberDbHelper;

    Button btn_add_ok;
    Button btn_add_reset;

    EditText et_add_id;
    EditText et_add_pw;
    EditText et_add_name;
    EditText et_add_age;
    EditText et_add_phone;
    EditText et_add_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add_activity);

        memberDbHelper = new MemberDbHelper(this);

        et_add_id = findViewById(R.id.et_id);
        et_add_pw = findViewById(R.id.et_pw);
        et_add_name = findViewById(R.id.et_name);
        et_add_phone = findViewById(R.id.et_phone);
        et_add_address = findViewById(R.id.et_address);
        et_add_age = findViewById(R.id.et_age);

        btn_add_ok = findViewById(R.id.btn_add_ok);
        btn_add_reset = findViewById(R.id.btn_add_reset);

        btn_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member member = new Member();
                member.setId(et_add_id.getText().toString());
                member.setPw(et_add_pw.getText().toString());
                member.setName(et_add_name.getText().toString());
                member.setAge(Integer.parseInt(et_add_age.getText().toString()));
                member.setPhone(et_add_phone.getText().toString());
                member.setAddress(et_add_address.getText().toString());

                boolean result = memberDbHelper.insert(member);

                Intent intent = new Intent();
                // 이 결과는 setResult에서 분기되어 넘어가므로 굳이 안넣어줘도됨.
                intent.putExtra("result", result);
                // member 객체에 serializable이 구현되어있어서 이 객체를 넣을수있다.
                intent.putExtra("add_member", member);

                setResult(result ? RESULT_OK : RESULT_CANCELED, intent);
                finish();
            }
        });

        btn_add_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_add_id.setText("");
                et_add_pw.setText("");
                et_add_name.setText("");
                et_add_age.setText("");
                et_add_phone.setText("");
                et_add_address.setText("");
            }
        });
    }
}
