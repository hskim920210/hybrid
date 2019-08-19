package com.tje.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MemoListDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MemoListDB.db";

    public static class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_FILENAME = "filename";
        public static final String COLUMN_NAME_DATE = "date";
    }

    private static final String SQL_CREATE_ENTRIES =
            String.format("create table %s ( %s integer primary key, %s text, %s text)",
                    MemoEntry.TABLE_NAME, MemoEntry._ID, MemoEntry.COLUMN_NAME_FILENAME, MemoEntry.COLUMN_NAME_DATE);

    public MemoListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Memo> loadMemoList() {
        ArrayList<Memo> memoArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                MemoEntry._ID, MemoEntry.COLUMN_NAME_FILENAME, MemoEntry.COLUMN_NAME_DATE
        };

        // query 메소드의 실행원리
        // select _ID, COLUMN_NAME_FILENAME, COLUMN_NAME_DATE
        //      from TABLE_NAME where 'selection' = 'selectionArgs의성분들'
        //          group by 'groupBy' having 'having' order by 'orderBy'
        Cursor cursor = db.query(MemoEntry.TABLE_NAME, projection, null, null, null, null, null);
        while(cursor.moveToNext()) {
            Memo memo = new Memo();
            memo.setFileName(cursor.getString(cursor.getColumnIndex(MemoEntry.COLUMN_NAME_FILENAME)));
            memo.setDate(cursor.getString(cursor.getColumnIndex(MemoEntry.COLUMN_NAME_DATE)));
            memoArrayList.add(memo);
        }
        return memoArrayList;
    }

    public void removeMemo(File file) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = MemoEntry.COLUMN_NAME_FILENAME;
        String[] selectionArgs = {file.getName()};

        db.delete(MemoEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table "+MemoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    public void saveMemo (File file) {
        if( !file.exists() ) {
            return;
        }
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoEntry.COLUMN_NAME_FILENAME, file.getName());
        values.put(MemoEntry.COLUMN_NAME_DATE, getDate());
        db.insert(MemoEntry.TABLE_NAME, null, values);
    }

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);
        String date = simpleDateFormat.format(new Date());
        return date;
   }
}
