package com.tje.database;

import androidx.appcompat.app.AppCompatActivity;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        memoWrite = findViewById(R.id.memo_write);
        memoListDbHelper = new MemoListDbHelper(this);
        Intent intent = getIntent();
        fileName = intent.getStringExtra("fileName");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMemo();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMemo();
            }
        });

        loadMemo();
    }

    private void deleteMemo() {
        File memoFile = getMemoFile();
        if(memoFile.exists()){
            memoListDbHelper.removeMemo(memoFile); // 삭제할 파일이 존재한다면 db에서도 삭제
            memoFile.delete();
        }
    }

    private void loadMemo() {
        File memoFile = getMemoFile();

        if(memoFile.exists()) {
            BufferedReader in;
            StringBuilder buffer = new StringBuilder();
            try {
                in = new BufferedReader(new FileReader(memoFile));
                String temp;
                while ( (temp = in.readLine()) != null ) {
                    buffer.append(temp+"\n");
                }
                memoWrite.setText(buffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void saveMemo() {
        String memo = memoWrite.getText().toString();
        /*
        if(memo.length() > 0) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                new FileOutputStream(getMemoFile()))));
                out.println(memo);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if( out != null ){
                    out.close();
                }
            }
        }
        */

        if(memo.length() > 0) {
            File memoFile = getMemoFile();
            boolean newFile = true;
            if(memoFile.exists()) {
                newFile = false;
            }
            FileOutputStream fileOutputStream;

            try {
                fileOutputStream = new FileOutputStream(memoFile);
                fileOutputStream.write(memo.getBytes());
                fileOutputStream.close();

                if(newFile) {
                    memoListDbHelper.saveMemo(memoFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private File getMemoFile() {
        if(fileName == null) {
            fileName = "memo-" + System.currentTimeMillis() + ".txt";
        }


        File file = new File(this.getApplicationContext().getExternalFilesDir("/"), fileName);
        Toast.makeText(getApplicationContext(), file.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
        return file;
        // return new File(Environment.getExternalStorageDirectory(), "memo.txt"); // API 29에서 안됨. API 28에선 됨
    }
}
