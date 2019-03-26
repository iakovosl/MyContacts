package com.example.mycontacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView2;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView2 = findViewById(R.id.textView2);
        String s = getIntent().getStringExtra("mymessage");
        //textView2.setText(s);
        String user_name = getIntent().getStringExtra("user_name");
        db = openOrCreateDatabase("DemoUsersDB", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT user_tel FROM DemoUser " +
                "WHERE user_name = '"+user_name+"';",null);
        cursor.moveToNext();
        String tel = cursor.getString(0);
        textView2.setText(tel);


    }
}
