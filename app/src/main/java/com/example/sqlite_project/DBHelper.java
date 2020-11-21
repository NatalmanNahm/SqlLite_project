package com.example.sqlite_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EventBrat";
    public static final String TABLE_NAME = "Events";
    public static final String TITLE_COL = "title";
    public static final String DATE_COL = "date";
    public static final String IMAGE_ID_COL = "img_id";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITLE_COL + "TEXT" +
                DATE_COL + "TEXT" +
                IMAGE_ID_COL + "INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
