package com.example.press;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "DB";
    // DBHelper 생성자
    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }
    // Exercise Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Exercise(Id INTEGER, Date TEXT, Exercise TEXT, Kg INTEGER, Reps INTEGER)");
    }

    // Exercise Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Exercise");
        onCreate(db);
    }

    // Exercise Table 데이터 입력
    public void insert(int id, String exercise, int kg, int reps) {
        SQLiteDatabase db = getWritableDatabase();
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);

        db.execSQL("INSERT INTO Exercise VALUES(" + id + ", '" + getTime + "','" + exercise + "', " + kg + ", " + reps + ")");
        db.close();
    }

    // Exercise Table 데이터 삭제
    public void Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE Exercise WHERE Id = " + id + "");
        db.close();
    }

    // Exercise Table 조회
    public List<Integer> getResult(String date, String exercise) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        List<Integer> result = new ArrayList<Integer>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Exercise WHERE Exercise='" + exercise + "' AND Date='" + date + "'", null);
//        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            result.add(cursor.getInt(3)); // kg
            result.add(cursor.getInt(4)); // reps
        }
        return result;
    }
    public List<Object> getResult(String exercise) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        List<Object> result = new ArrayList<Object>();

        Cursor cursor = db.rawQuery("SELECT * FROM Exercise WHERE Exercise='" + exercise + "'", null);
//        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(1)); // Date
            result.add(cursor.getInt(3)); // kg
            result.add(cursor.getInt(4)); // reps
        }
        return result;
    }

    public List<Object> getResultByDate(String exercise) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        List<Object> result = new ArrayList<Object>();

        Cursor cursor = db.rawQuery("SELECT Date, sum(Kg*Reps) FROM Exercise WHERE Exercise='" + exercise + "' GROUP BY Date", null);
//        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0)); // Date
            result.add(cursor.getInt(1)); // kg * reps
        }
        return result;
    }

    public int getLastIndex(String exercise){
        SQLiteDatabase db = getReadableDatabase();
        int lastIndex = 1;
        Cursor cursor = db.rawQuery("SELECT * FROM Exercise WHERE Exercise='" + exercise + "'", null);
        while (cursor.moveToNext()) {
            lastIndex++;
        }
        return lastIndex;
    }
    public int getMaxOneRm(String exercise){
        SQLiteDatabase db = getReadableDatabase();
        int oneRm;
        Cursor cursor = db.rawQuery("SELECT max(Kg) FROM Exercise WHERE Exercise='" + exercise + "'", null);
        cursor.moveToNext();
        try {
            oneRm = cursor.getInt(0);
        } catch (CursorIndexOutOfBoundsException e){
            oneRm = 0;
        }
        return oneRm;
    }
    public void deleteLastSet(String exercise, int index){
        SQLiteDatabase db = getReadableDatabase();
        try {
            db.rawQuery("DELETE FROM Exercise WHERE Exercise='" + exercise + "' AND Id=" + index, null);
        } catch (Exception e){
            Log.d("Delete", "Nothing to delete.");
        }
    }
}



