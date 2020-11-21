package com.example.sqlite_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CreateEventActivity.class);
        FloatingActionButton fab = findViewById(R.id.add_event_btn);
        fab.setOnClickListener((view) -> startActivity(intent));

    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbHelper = new DBHelper(this, DBHelper.DATABASE_NAME, null, 1);
        SQLiteDatabase reader = dbHelper.getReadableDatabase();
        String[] columns = {"_id", DBHelper.TITLE_COL, DBHelper.DATE_COL, DBHelper.IMAGE_ID_COL};
        Cursor cursor = reader.query(DBHelper.TABLE_NAME, columns, null, null,
                null, null, null);


    }
}