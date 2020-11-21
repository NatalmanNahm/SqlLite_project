package com.example.sqlite_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        //Setting up a spinner
        Spinner eventType = findViewById(R.id.event_image_type);
        ArrayAdapter<CharSequence> types =
                ArrayAdapter.createFromResource(this, R.array.event_type,
                        android.R.layout.simple_spinner_dropdown_item);
        eventType.setAdapter(types);

        //the database is getting called
        DBHelper dbHelper = new DBHelper(this, DBHelper.DATABASE_NAME, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //referencing items
        Button createBtn = findViewById(R.id.create_btn);
        EditText title = findViewById(R.id.event_title_edit);
        EditText date = findViewById(R.id.event_date);

        createBtn.setOnClickListener((view) -> {
            String titleStr = title.getText().toString();
            String dateStr = date.getText().toString();
            String typeKey = eventType.getSelectedItem().toString().toLowerCase();
            if (TextUtils.isEmpty(titleStr) || TextUtils.isEmpty(dateStr) ||
                    TextUtils.isEmpty(typeKey)) {
                Log.i("DEBUG", "EMPTY VALUE");
                return;
            }
        });
    }
}