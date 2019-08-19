package com.tje.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MemoWriteActivity extends AppCompatActivity {

    private Button save;
    private Button delete;
    private EditText memoWrite;

    private MemoListDbHelper memoListDbHelper;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_write);

        memoListDbHelper = new MemoListDbHelper(this);

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        memoWrite = findViewById(R.id.memo_write);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveMemo();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMemo();
            }
        });

        Intent intent = getIntent();
        fileName = intent.getStringExtra("fileName");

        loadMemo();
    }

    private void saveMemo() {
        String memo = memoWrite.getText().toString();

        if (memo.length() > 0) {
            PrintWriter out = null;

            File memoFile = getMemoFile();
            boolean newFile = true;
            if (memoFile.exists()) {
                newFile = false;
            }

            try {
                out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(
                                                memoFile))));
                out.println(memo);
                out.flush();

                if (newFile)
                    memoListDbHelper.saveMemo(memoFile);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if( out != null )
                    out.close();
                else
                    Toast.makeText(getApplicationContext(), "스트림 종료 에러", Toast.LENGTH_SHORT);
            }
        }
    }

    private void loadMemo() {
        File memoFile = getMemoFile();

        if (memoFile.exists()) {
            BufferedReader in = null;
            StringBuilder buffer = new StringBuilder();
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(memoFile)));
                String temp;
                while( (temp = in.readLine()) != null )
                    buffer.append(temp + "\n");
                memoWrite.setText(buffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                if( in != null ) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void deleteMemo() {
        File memoFile = getMemoFile();
        if (memoFile.exists()) {
            memoListDbHelper.removeMemo(memoFile);
            memoFile.delete();
        }
    }

    private File getMemoFile(){
        if (fileName == null) {
            fileName = "memo-" + System.currentTimeMillis() + ".txt";
        }

        File file = new File(
                this.getApplicationContext().getExternalFilesDir("/"),
                fileName);

        // Toast.makeText(getApplicationContext(), file.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
        return file;
}
}
