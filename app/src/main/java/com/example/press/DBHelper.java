package com.example.press;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "DB";
    // DBHelper 생성자
    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }
    // Exercise Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Exercise(Date TEXT, Exercise TEXT, Kg INTEGER, Reps INTEGER)");
    }

    // Exercise Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Exercise");
        onCreate(db);
    }

    // Exercise Table 데이터 입력
    public void insert(String exercise, int kg, int reps) {
        SQLiteDatabase db = getWritableDatabase();
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);

        db.execSQL("INSERT INTO Exercise VALUES('" + getTime + "','" + exercise + "', " + kg + ", " + reps + ")");
        db.close();
    }

    // Exercise Table 데이터 삭제
    public void Delete(String exercise) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE Exercise WHERE Exercise = '" + exercise + "'");
        db.close();
    }

    // Exercise Table 조회
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Exercise", null);
        while (cursor.moveToNext()) {
            result += " 날짜 : "
                    + cursor.getString(0)
                    + ", 운동 : "
                    + cursor.getString(1)
                    + ", 무게 : "
                    + cursor.getInt(2)
                    + ", 횟수 : "
                    + cursor.getInt(3)
                    + "\n";
        }
        return result;
    }
}



