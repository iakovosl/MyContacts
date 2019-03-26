package com.example.mycontacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        db = openOrCreateDatabase("DemoUsersDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS DemoUser ("+
                "user_id TEXT PRIMARY KEY," +
                "user_name TEXT UNIQUE," +
                "user_tel TEXT);");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('1','User1','21022222333');");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('2','User2','21022222334');");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('3','User3','21022222335');");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('4','User4','21022222336');");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('5','User5','21022222337');");
        db.execSQL("INSERT OR IGNORE INTO DemoUser VALUES('6','User6','21022222338');");

    }

    public void go(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        String input = editText.getText().toString();
        intent.putExtra("mymessage",input);
        startActivity(intent);
    }
    public void selectall (View view){
        Cursor cursor = db.rawQuery("SELECT * FROM DemoUser ORDER BY user_id ASC ", null);
        if (cursor.getCount()==0)
            showMessage("Error", "No records found");
        else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                buffer.append("User id: "+cursor.getString(0)+"\n");
                buffer.append("User name: "+cursor.getString(1)+"\n");
                buffer.append("User tel: "+cursor.getString(2)+"\n");
                buffer.append("------------------------------------\n");
            }
            cursor.close();
            showMessage("Records",buffer.toString());

        }

    }
    public void delete (View view){
        try{
            db.execSQL("DELETE FROM DemoUser WHERE user_name = 'User6';");
            Toast.makeText(this,"Deleted!",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void update(View view){
        db.execSQL("UPDATE DemoUser SET user_tel = '2111111111' WHERE user_name = 'User6';");
        Toast.makeText(this,"Updated!",Toast.LENGTH_SHORT).show();
    }

    public void find (View view){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("user_name","User1");
        startActivity(intent);
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
