package com.tje.dialogexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 객체 생성
                // 현재 부모창의 이름을 알면 접근하여 this로 선택가능
                AlertDialog.Builder dlg =
                        new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("첫번째 다이얼로그 제목");
                dlg.setMessage("첫번째 다이얼로그 내용");
                dlg.show();
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 객체 생성
                // 현재 부모창의 이름을 알면 접근하여 this로 선택가능
                AlertDialog.Builder dlg =
                        new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("직업을 선택하세요");
                final String[] job_list = {"학생", "회사원", "공무원"};

                // 리스너를 추가하지 않아서 클릭 이벤트에 대해 동작하지 않는다.
                // dlg.setItems(job_list, null);

                dlg.setItems(job_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String msg = String.format("선택된 항목은 %s 입니다.", job_list[i]);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setPositiveButton("확인", null);

                dlg.show();
            }
        });

        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 객체 생성
                // 현재 부모창의 이름을 알면 접근하여 this로 선택가능
                AlertDialog.Builder dlg =
                        new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("직업을 선택하세요");
                final String[] job_list = {"학생", "회사원", "공무원"};

                dlg.setSingleChoiceItems(job_list, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String msg = String.format("선택된 항목은 %s 입니다.", job_list[i]);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setPositiveButton("확인", null);

                dlg.show();
            }
        });

        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 객체 생성
                // 현재 부모창의 이름을 알면 접근하여 this로 선택가능
                AlertDialog.Builder dlg =
                        new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("관심사를 선택하세요");
                final String[] focus_list = {"정치", "사회", "스포츠", "경제", "인간관계"};

                dlg.setMultiChoiceItems(focus_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        String msg = String.format("현재 선택된 항목은 %s(%b) 입니다.", focus_list[i], b);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setPositiveButton("확인", null);

                dlg.show();
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 레이아웃 파일을 사용하여 새로운 다이얼로그 화면을 구성할 View 객체를 생성
                // inflate : 부풀려주는것.
                // inflate 메소드
                // 레이아웃 파일을 사용하여 다이얼로그를 구성할 View 객체를 생성하는 메소드
                // inflate(부모액티비티객체, 레이아웃파일의ID, 그룹레퍼런스(null로하면독립적창));
                final View dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog_login, null);
                AlertDialog.Builder dlg =
                        new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("로그인");

                // inflate 메소드를 통해 생성된 View 객체를 사용하여
                // 다이얼로그를 생성하는 경우 View 객체를 통해 사용자가 입력한 내용을 추출할 수 있다.
                dlg.setView(dialogView);
                // 레이아웃 파일을 사용하여 View를 생성하는 경우
                // 직접적으로 사용자가 입력한 내용을 추출할 수 없다.
                // (구성만 가능)
                // dlg.setView(R.layout.dialog_login);
                dlg.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // findViewById 메소드는 현재 액티비티에 활성화된
                        // View 객체를 반환하는 메소드
                        // dialog_login의 것을 가져올 수 없다.
                        // EditText id = findViewById(R.id.et_id);
                        // EditText pw = findViewById(R.id.et_pw);
                        EditText id = dialogView.findViewById(R.id.et_id);
                        EditText pw = dialogView.findViewById(R.id.et_pw);

                        String msg = String.format("ID : %s, PW : %s", id.getText().toString(), pw.getText().toString());
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
    }
}
