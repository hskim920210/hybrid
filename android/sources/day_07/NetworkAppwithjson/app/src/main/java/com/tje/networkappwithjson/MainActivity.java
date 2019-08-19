package com.tje.networkappwithjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_load;
    RecyclerView memberRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    MemberRecyclerViewAdapter memberAdapter;

    ArrayList<Member> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_load = findViewById(R.id.btn_load);

        memberRecyclerView = findViewById(R.id.memberRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        memberRecyclerView.setLayoutManager(layoutManager);
        memberAdapter = new MemberRecyclerViewAdapter();

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  String strUrl = "http://192.168.0.18";
            }
        });
    }
}
