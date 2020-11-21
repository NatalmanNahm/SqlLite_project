package com.example.sqlite_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, Integer> images = new HashMap<>();
        images.put("party", R.drawable.party);
        images.put("concert", R.drawable.concert);
        images.put("gathering", R.drawable.business_meeting);
        images.put("food", R.drawable.food);

        createBtn.setOnClickListener((view) -> {
            String titleStr = title.getText().toString();
            String dateStr = date.getText().toString();
            String typeKey = eventType.getSelectedItem().toString().toLowerCase();
            if (TextUtils.isEmpty(titleStr) || TextUtils.isEmpty(dateStr) ||
                    TextUtils.isEmpty(typeKey)) {
                Log.i("DEBUG", "EMPTY VALUE");
                return;
            }
            Log.i("INFO", String.format("saving %s, %s, %s", titleStr, dateStr, typeKey));
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.TITLE_COL, titleStr);
            cv.put(DBHelper.DATE_COL, dateStr);
            cv.put(DBHelper.IMAGE_ID_COL, images.get(typeKey));

            //inserting data into the table
            db.insert(DBHelper.TABLE_NAME, null, cv);
            title.setText("");
            date.setText("");
            Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show();
            db.close();
//            onBackPressed();
        });
    }
}