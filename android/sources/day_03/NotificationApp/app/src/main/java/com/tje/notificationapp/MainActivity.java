package com.tje.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String NOTI_C_ID = "c1_id";
    private static final String NOTI_C_NAME = "c1_name";

    int notificationID = 0;

    Button btn;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.notificationBtn);
        msg = findViewById(R.id.notificationMsg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotificationMsg();
            }
        });
    }

    public void showNotificationMsg() {
        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importances = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTI_C_ID, NOTI_C_NAME, importances);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        Editable input = msg.getText();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), NOTI_C_ID);

        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("알림 타이틀");
        builder.setContentText(input);
        notificationManager.notify(notificationID, builder.build());

        notificationID++;
    }
}
