package com.tje.membermanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        layoutManager = new LinearLayoutManager(this); // 현재 액티비티에 대한 레이아웃 매니저
        memberRecyclerView.setLayoutManager(layoutManager);
        memberAdapter = new MemberRecyclerViewAdapter(memberDbHelper,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int position = memberRecyclerView.getChildAdapterPosition(view);
                        final Member member = memberAdapter.getItem(position);
                        String msg = String.format("ID : %s, NAME : %s", member.getId(), member.getName());
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("멤버 삭제");
                        dlg.setMessage(String.format("'%s'('%s') 회원을 삭제하시겠습니까?", member.getId(), member.getName()));
                        dlg.setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                memberDbHelper.delete(member);
                                MainActivity.this.onResume();
                                // 같은 화면에 있다보니 리사이클러 뷰를 초기화시킴
                                memberAdapter.notifyDataSetChanged();
                                // memberAdapter.notifyItemRemoved(position);
                            }
                        });
                        dlg.setNegativeButton("취소", null);
                        dlg.create().show();

                    }
                });
        memberRecyclerView.setAdapter(memberAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemberAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });
    }

    // p 129에 액티비티의 생명주기

    // 화면이 복귀될 때 아래의 것을 체크하여 보일건 보이게, 안보일건 안보이게 한다.
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Count : " + memberDbHelper.count(), Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_INSERT :
                if( data == null ) {
                    Toast.makeText(this, "회원가입을 취소했습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // object로 반환되어 형변환이 필요하다.
                Member member = (Member) data.getSerializableExtra("add_member");
                String msg;
                if(resultCode == RESULT_OK) {
                    msg = String.format("'%s' 아이디로 '%s' 님의 회원가입이 완료되었습니다.", member.getId(), member.getName());

                } else {
                    msg = String.format("'%s' 님의 회원가입 요청이 실패했습니다.", member.getName());
                }
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                break;
            case REQUEST_CODE_UPDATE :
                break;
            case REQUEST_CODE_DELETE :
                break;
        }
    }
}
