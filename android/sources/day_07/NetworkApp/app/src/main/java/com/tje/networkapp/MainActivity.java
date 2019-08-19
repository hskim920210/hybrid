package com.tje.networkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText et_id;
    EditText et_pw;
    TextView tv_login_result;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        tv_login_result = findViewById(R.id.tv_login_result);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 서버로 사용자가 입력한 ID/PW를 전달하여
                // 로그인 처리의 결과를 반환받는 이벤트 처리 코드
                final String strId = et_id.getText().toString().trim();
                final String strPw = et_pw.getText().toString().trim();

                if(strId.length() == 0 || strPw.length() == 0) {
                    Toast.makeText(MainActivity.this, "ID와 PW는 반드시 입력되야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Toast.makeText(MainActivity.this, "ID:"+strId+", PW:"+strPw, Toast.LENGTH_SHORT).show();

                // 로그인 처리를 위한 서버의 URL 변수
                final String strUrl = "http://192.168.0.18:8080/android/login";

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(strUrl);
                            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                            // POST 방식으로 서버에 전달할 데이터 생성
                            String params = String.format("id=%s&pw=%s", strId, strPw);

                            // 서버의 데이터를 전송하기 위한 스트림 생성
                            // 1, HttpURLConnection 객체의 setDoOutPut() 메소드의 매개변수로 true를 전달
                            //    (POST 방식으로 데이터를 전송하는 경우에만 작성)
                            connection.setDoOutput(true);
                            // 2. 출력스트림 생성
                            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                            // 3. 데이터 출력과 flush
                            writer.write(params);
                            writer.flush();
                            final int code = connection.getResponseCode(); // getInputStream을 하거나 getResponseCode 하는 순간 request가 넘어간다.
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Toast.makeText(MainActivity.this, "code : " + code, Toast.LENGTH_SHORT).show();
                                }
                            });

                            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                            StringBuilder input = new StringBuilder();
                            String line;
                            while( (line = reader.readLine()) != null ){
                                input.append(line);
                            }
                            JSONObject jsonObject = new JSONObject(input.toString());
                            final String r_id = jsonObject.getString("id");
                            final String r_pw = jsonObject.getString("pw");
                            final String r_r = jsonObject.getString("r");

                            //
                            final String input_str = input.toString();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_login_result.setText(r_id+", "+r_pw+", "+r_r);
                                    tv_login_result.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "Response Json : " + input_str, Toast.LENGTH_SHORT).show();
                                }
                            });

                            writer.close();
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
