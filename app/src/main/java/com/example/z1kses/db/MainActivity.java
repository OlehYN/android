package com.example.z1kses.db;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    //create variables for storing message & button objects
    EditText msgTextField;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make message text field object
        msgTextField = (EditText) findViewById(R.id.msgTextField);
        //make button object
        sendButton = (Button) findViewById(R.id.sendButton);

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        dbh.insertPosition(db, "Програміст", 100);
        dbh.insertPerson(db, "Петренко Петро", "Програміст");

        dbh.insertPosition(db,"Продавець",25);
        dbh.insertPerson(db,"Іваненко Іван", "Продавець");

        dbh.insertPosition(db,"Консультант",50);
        dbh.insertPerson(db,"Костенко Василь", "Консультант");

        dbh.insertPosition(db,"Вчитель",75);
        dbh.insertPerson(db,"Василенко Інокетній", "Вчитель");
    }

    //When the send button is clicked
    public void send(View v)
    {
        Intent myIntent = new Intent(this, EnitityActivity.class);
        myIntent.putExtra("name", msgTextField.getText().toString());
        startActivity(myIntent);
    }
}
