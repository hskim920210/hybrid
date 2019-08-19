package com.tje.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

    private Button btn_linear;
    private Button btn_grid;
    private Button btn_new_note;

    private static final int REQUEST_USED_PERMISSION = 200;

    private static final String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean permissionToFileAccepted = true;

        switch (requestCode){
            case REQUEST_USED_PERMISSION:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        permissionToFileAccepted = false;
                        break;
                    }
                }
                break;
        }
        if (!permissionToFileAccepted){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (String permission : needPermissions) {
            if (ActivityCompat.checkSelfPermission(
                    this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this, needPermissions, REQUEST_USED_PERMISSION);
                break;
            }
        }

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        loadRecyclerViewMode();
        //layoutManager = new LinearLayoutManager(this);
        //layoutManager.setAutoMeasureEnabled(false);
        //recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        btn_linear = findViewById(R.id.btn_linear);
        btn_grid = findViewById(R.id.btn_grid);
        btn_new_note = findViewById(R.id.btn_new_note);

        btn_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToLinearLayout();
            }
        });

        btn_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToGridLayout();
            }
        });

        btn_new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this, MemoWriteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void changeToLinearLayout() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        saveRecyclerViewMode("linear");
    }

    private void changeToGridLayout() {
        layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        saveRecyclerViewMode("grid");
    }

    private void saveRecyclerViewMode(String mode) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "note_view", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("mode", mode);
        editor.commit();
    }

    private void loadRecyclerViewMode() {

        SharedPreferences sharedPreferences = getSharedPreferences(
                "note_view", Context.MODE_PRIVATE);
        String mode = sharedPreferences.getString("mode", "linear");

        if( mode.equals("linear") )
            changeToLinearLayout();
        else
            changeToGridLayout();
    }
}











