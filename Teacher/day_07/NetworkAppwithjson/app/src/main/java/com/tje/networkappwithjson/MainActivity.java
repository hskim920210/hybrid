package com.tje.networkappwithjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
        memberRecyclerView.setAdapter(memberAdapter);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strUrl = "http://192.168.0.48:8080/android/list";

                new Thread(){
                    @Override
                    public void run() {

                        try {
                            URL url = new URL(strUrl);
                            HttpURLConnection connection =
                                    (HttpURLConnection)url.openConnection();

                            BufferedReader reader =
                                    new BufferedReader(
                                            new InputStreamReader(
                                                    connection.getInputStream(),
                                                    "utf-8"));

                            StringBuilder input = new StringBuilder();
                            String line;
                            while( (line = reader.readLine()) != null )
                                input.append(line);

                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<Member>>(){}.getType();
                            list = gson.fromJson(input.toString(), listType);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    memberAdapter.loadItems(list);
                                    memberAdapter.notifyDataSetChanged();

                                    Toast.makeText(MainActivity.this,
                                            "읽어온 멤버의 수 : " + list.size(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();

            }
        });
    }
}










