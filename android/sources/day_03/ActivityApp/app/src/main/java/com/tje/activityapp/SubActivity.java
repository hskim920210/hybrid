package com.tje.activityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    Button btn_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        // startActivity 메소드에 의해 실행되는 액티비티는
        // - getIntent 메소드를 사용하여 화면 전환에 사용된
        //   intent 객체를 추출할 수 있다.
        Intent intent = getIntent();

        String msg = intent.getStringExtra("msg");
        ((TextView)findViewById(R.id.textView_sub)).setText(msg);

        btn_sub = findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이미 돌아갈 장소가 결정되어있다. (지금은 Sub이므로 갈곳은 main밖에)
                // 그래서 intent 변수에 두개가 들어가지 않음
                Intent i = new Intent();
                i.putExtra("result", "화면전환 완료!");
                // setResult 메소드
                // startActivity 메소드에 의해서 전환된 액티비티는
                // 현재 액티비티에서 실행된 결과를 Intent 객체에 저장하고
                // 상태 코드와 함께 setResult 메소드를 통해 Main 액티비티로 전달할 수 있다.
                // Activity.RESULT_OK : 이상없이 잘 종료됨
                // Activity.RESULT_CANCELED : 처리 과정에서 문제가 발생
                setResult(Activity.RESULT_OK, i);
                // 액티비티를 종료하는 finish 메소드
                // - 전환된 액티비티를 종료하고, Main 액티비티로 이동
                finish();
            }
        });
    }
}
