package com.tje.networkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    /*
    http://192.168.0.18:8080/android
    = HOST_NETWORK_PROTOCOL + HOST_ADDRESS + HOST_APP_NAME
    */
    private static final String HOST_NETWORK_PROTOCOL = "http://";
    private static final String HOST_ADDRESS = "192.168.0.18:8080";
    private static final String HOST_APP_NAME = "/android";

    EditText et_msg;
    Button btn_send_get;
    Button btn_send_post;
    Button btn_login;
    Button btn_logout;
    TextView tv_receive_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_msg = findViewById(R.id.et_msg);
        btn_send_get = findViewById(R.id.btn_send_get);
        btn_send_post = findViewById(R.id.btn_send_post);
        btn_login = findViewById(R.id.btn_login);
        btn_logout = findViewById(R.id.btn_logout);
        tv_receive_msg = findViewById(R.id.tv_receive_msg);

        btn_send_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                비동기 방식으로 통신을 처리할 수 있는 예제.
                AsyncTask 클래스
                비동기 방식으로 백그라운드 작업을 처리할 때
                사용할 수 있는 클래스.
                수 초 정도의 작업에 적합하다.
                */
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // GET 방식에 실어 보낼 메세지
                        String msg = et_msg.getText().toString().trim();
                        // GET 방식에 실어 보낼 쿼리스트링 param
                        String param = null;
                        if(msg.length() > 0){
                            param = String.format("?msg=%s", msg);
                        }
                        String targetURL = "/request_get" + (param != null ? param : "");

                        try {
                            URL endPoint = new URL(MainActivity.HOST_NETWORK_PROTOCOL +
                                                    MainActivity.HOST_ADDRESS +
                                                    MainActivity.HOST_APP_NAME +
                                                    targetURL);
                            HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();

                            // HTTP 접속 방식 설정. 기본값은 GET이다.
                            connection.setRequestMethod("GET");

                            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                                // 연결 및 데이터 수신 성공
                                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(connection.getInputStream(), "utf-8"));

                                // JSON 포맷을 처리하기 위한 객체 생성
                                Gson gson = new Gson();
                                // 서버에서 전달한 JSON 문서를 사용하여 Member 클래스의 객체로 변환
                                // Toast.makeText(getApplicationContext(), in.toString(), Toast.LENGTH_SHORT).show();
                                final Member member = gson.fromJson(in, Member.class);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringBuilder msg = new StringBuilder();
                                        msg.append("NAME : " + member.getName() + "\n");
                                        msg.append("AGE : " + member.getAge() + "\n");
                                        msg.append("TEL : " + member.getTel() + "\n");
                                        msg.append("IMAGE : " + member.getImageUri() + "\n");

                                        tv_receive_msg.setText(msg.toString());
                                    }
                                });
                            } else {
                                // 통신 에러
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_receive_msg.setText("통신 에러 ...");
                                    }
                                });
                            }
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });

        btn_send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String targetURL = "/request_post";

                        String msg = et_msg.getText().toString().trim();
                        String param = null;
                        if(msg.length() > 0) {
                            param = String.format("msg=%s", msg);
                        }
                        URL endPoint = null;
                        try {
                            endPoint = new URL(MainActivity.HOST_NETWORK_PROTOCOL +
                                    MainActivity.HOST_ADDRESS +
                                    MainActivity.HOST_APP_NAME +
                                    targetURL);
                            HttpURLConnection connection = (HttpURLConnection)endPoint.openConnection();

                            connection.setRequestMethod("POST");

                            if(param != null){
                                // 파라메터 값 POST 방식 전송. OutputStream에 실어 보낸다.
                                connection.setDoOutput(true);
                                connection.getOutputStream().write(param.getBytes());
                            }

                            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                                Gson gson = new Gson();

                                final Member member = gson.fromJson(in, Member.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringBuilder msg = new StringBuilder();
                                        msg.append("NAME : " + member.getName() + "\n");
                                        msg.append("AGE : " + member.getAge() + "\n");
                                        msg.append("TEL : " + member.getTel() + "\n");
                                        msg.append("IMAGE : " + member.getImageUri() + "\n");
                                        tv_receive_msg.setText(msg.toString());
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_receive_msg.setText("통신 에러 ...");
                                    }
                                });
                            }
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String msg = et_msg.getText().toString().trim();
                        StringTokenizer tokenizer = new StringTokenizer(msg);

                        if(tokenizer.countTokens() != 2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_receive_msg.setText("ID PW 형식으로 입력하세요.");
                                }
                            });
                            return;
                        }
                        String id = tokenizer.nextToken();
                        String pw = tokenizer.nextToken();
                        String param = String.format("id=%s&pw=%s", id, pw);

                        String targetURL = "/app_login";
                        try {
                            URL endPoint = new URL(MainActivity.HOST_NETWORK_PROTOCOL +
                                    MainActivity.HOST_ADDRESS +
                                    MainActivity.HOST_APP_NAME + targetURL);

                            HttpURLConnection connection = (HttpURLConnection)endPoint.openConnection();

                            // 세션 사용을 위한 쿠키 값 추출 (기존에 저장된 쿠키의 값 추출)
                            String cookieString = CookieManager.getInstance().getCookie(
                                                    MainActivity.HOST_NETWORK_PROTOCOL +
                                                    MainActivity.HOST_ADDRESS +
                                                    MainActivity.HOST_APP_NAME );

                            // 서버로 요청을 전달할 때, 사전에 저장된 쿠키의 값을 전달하는 방법
                            if(cookieString != null) {
                                connection.setRequestProperty("Cookie", cookieString);
                            }

                            connection.setRequestMethod("POST");
                            connection.setDoOutput(true);
                            connection.getOutputStream().write(param.getBytes());

                            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                // 서버로부터 수신된 쿠키의 값을 CookieManager에 저장
                                Map<String, List<String>> headerFields = connection.getHeaderFields();
                                String COOKIES_HEADER = "Set-Cookie";
                                List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

                                if(cookiesHeader!=null){
                                    for(String cookie : cookiesHeader) {
                                        String cookieName = HttpCookie.parse(cookie).get(0).getName();
                                        String cookieValue = HttpCookie.parse(cookie).get(0).getValue();

                                        cookieString = cookieName + " = " + cookieValue;
                                        CookieManager.getInstance().setCookie(MainActivity.HOST_NETWORK_PROTOCOL +
                                                MainActivity.HOST_ADDRESS + MainActivity.HOST_APP_NAME, cookieString);
                                    }
                                }

                                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                                // JSON 포맷을 처리하기 위한 객체 생성
                                JsonParser parser = new JsonParser();
                                JsonElement element = parser.parse(in);
                                final String login_result = element.getAsJsonObject().get("result").getAsString();
                                final String login_msg = element.getAsJsonObject().get("login_msg").getAsString();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(login_result.equals("true")){
                                            tv_receive_msg.setText("로그인 성공");
                                        } else {
                                            tv_receive_msg.setText("로그인 실패\n");
                                            tv_receive_msg.append(login_msg);
                                        }
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_receive_msg.setText("통신 에러 ...");
                                    }
                                });
                            }
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String targetURL = "/app_logout";

                        try {
                            URL endPoint = new URL(MainActivity.HOST_NETWORK_PROTOCOL +
                                    MainActivity.HOST_ADDRESS + MainActivity.HOST_APP_NAME + targetURL);

                            HttpURLConnection connection = (HttpURLConnection)endPoint.openConnection();

                            String cookieString = CookieManager.getInstance().getCookie(
                                    MainActivity.HOST_NETWORK_PROTOCOL + MainActivity.HOST_ADDRESS +
                                            MainActivity.HOST_APP_NAME);

                            if(cookieString != null) {
                                connection.setRequestProperty("Cookie", cookieString);
                            } else {
                                return;
                            }

                            connection.setRequestMethod("GET");

                            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                // 서버로부터 수신된 쿠키의 값을 CookieManager에 저장
                                Map<String, List<String>> headerFields = connection.getHeaderFields();
                                String COOKIES_HEADER = "Set-Cookie";
                                List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

                                if(cookiesHeader!=null) {
                                    for(String cookie : cookiesHeader){
                                        String cookieName = HttpCookie.parse(cookie).get(0).getName();
                                        String cookieValue = HttpCookie.parse(cookie).get(0).getValue();

                                        cookieString = cookieName + " = " + cookieValue;
                                        CookieManager.getInstance().setCookie(
                                                MainActivity.HOST_NETWORK_PROTOCOL + MainActivity.HOST_ADDRESS +
                                                        MainActivity.HOST_APP_NAME, cookieString);

                                    }
                                }

                                BufferedReader in = new BufferedReader(new InputStreamReader(
                                            connection.getInputStream(), "utf-8"));

                                // JSON 포맷을 처리하기 위한 객체 생성
                                JsonParser parser = new JsonParser();
                                JsonElement element = parser.parse(in);
                                final String logout_result = element.getAsJsonObject().get("result").getAsString();
                                final String logout_msg = element.getAsJsonObject().get("logout_msg").getAsString();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(logout_result.equals("true")){
                                            tv_receive_msg.setText("로그아웃 성공\n");
                                        } else {
                                            tv_receive_msg.setText("로그아웃 실패\n");
                                        }
                                        tv_receive_msg.append(logout_msg);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_receive_msg.setText("통신 에러 ...");
                                    }
                                });
                            }
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}