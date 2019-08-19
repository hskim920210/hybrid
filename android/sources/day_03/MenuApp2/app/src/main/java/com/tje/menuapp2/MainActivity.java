package com.tje.menuapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_1;
    Button btn_2;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        layout = findViewById(R.id.layout_main);

        // 컨텍스트 메뉴를 등록하기 위한 메소드 호출
        // (특정 View 객체를 길게 누르는 경우 메뉴가 활성화 되도록 처리할 수 있다.)
        registerForContextMenu(btn_1);
        registerForContextMenu(btn_2);
    }

    // registerForContextMenu 메소드 호출 후,
    // registerForContextMenu에 사용된 View 객체를
    // 컨텍스트 메뉴와 연결하기 위한 메소드를 오버라이딩
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        switch (v.getId()) {
            case R.id.btn_1 :
                inflater.inflate(R.menu.menu1, menu);
                break;
            case R.id.btn_2 :
                inflater.inflate(R.menu.menu2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1 :
                Toast.makeText(getApplicationContext(), "메뉴 1 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_2 :
                Toast.makeText(getApplicationContext(), "메뉴 2 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_3 :
                Toast.makeText(getApplicationContext(), "메뉴 3 선택", Toast.LENGTH_SHORT).show();
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
