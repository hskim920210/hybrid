package com.tje.membermanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INSERT = 1;
    private static final int REQUEST_CODE_UPDATE = 2;
    private static final int REQUEST_CODE_DELETE = 3;

    MemberDbHelper memberDbHelper;

    Button btn_add;
    Button btn_update;
    Button btn_delete;

    TextView tv_no_member;
    RecyclerView memberRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    MemberRecyclerViewAdapter memberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberDbHelper = new MemberDbHelper(this);

        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        tv_no_member = findViewById(R.id.tv_no_member);
        memberRecyclerView = findViewById(R.id.memberRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        memberRecyclerView.setLayoutManager(layoutManager);

        memberAdapter = new MemberRecyclerViewAdapter(memberDbHelper);
        memberRecyclerView.setAdapter(memberAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemberAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "COUNT : " + memberDbHelper.count(),
                Toast.LENGTH_SHORT).show();

        if( memberDbHelper.count() == 0 ) {
            tv_no_member.setVisibility(View.VISIBLE);
            memberRecyclerView.setVisibility(View.GONE);
        } else {
            memberAdapter.loadItems();
            tv_no_member.setVisibility(View.GONE);
            memberRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, @Nullable Intent data) {

        switch( requestCode ) {
            case REQUEST_CODE_INSERT :
                if( data == null ) {
                    Toast.makeText(this,
                            "회원가입을 취소했습니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Member member = (Member)data.getSerializableExtra("add_member");

                String msg;
                if( resultCode == RESULT_OK )
                    msg = String.format("'%s' 아이디로 '%s' 님의 회원가입이 완료되었습니다",
                            member.getId(), member.getName());
                else
                    msg = String.format("'%s' 님의 회원가입 요청이 실패했습니다.",
                            member.getName());
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                break;

            case REQUEST_CODE_UPDATE :
                break;
            case REQUEST_CODE_DELETE :
                break;
        }

    }
}











