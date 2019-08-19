package com.tje.menuapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_layout);
    }

    // 안드로이드 앱에서 메뉴를 사용하려는 경우
    // 오버라이딩 해야하는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // 메뉴 리소스를 등록하는 코드
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);

        return true;
    }

    // 메뉴가 선택되는 이벤트가 발생될 때
    // 실행되는 메소드를 정의
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 현재 클릭된 메뉴의 아이디를 사용하여 분기문을 작성
        switch( item.getItemId() ) {
            case R.id.item_1 :
                Toast.makeText(getApplicationContext(),
                        "메뉴 1번 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_2 :
                Toast.makeText(getApplicationContext(),
                        "메뉴 2번 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_3 :
                Toast.makeText(getApplicationContext(),
                        "메뉴 3번 선택", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_red :
                this.layout.setBackgroundColor(Color.RED);
                break;

            case R.id.item_blue :
                this.layout.setBackgroundColor(Color.BLUE);
                break;

            case R.id.item_green :
                this.layout.setBackgroundColor(Color.GREEN);
                break;

        }

        return true;
    }
}











