package com.example.z1kses.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_PERSON = "persons";
    public static final String PERSON_NAME = "name";
    public static final String PERSON_POSITION = "position";

    public static final String TABLE_POSITION = "positions";
    public static final String POSITION_POSITION = "id";
    public static final String POSITION_SALARY= "salary";

    private static final String DATABASE_NAME = "third.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_PERSON = "create table "
            + TABLE_PERSON + "(" + PERSON_NAME
            + " text primary key, " + PERSON_POSITION
            + " text not null," +
            "  FOREIGN KEY("+PERSON_POSITION+") REFERENCES "+TABLE_POSITION+"("+POSITION_POSITION+"));";

    private static final String DATABASE_CREATE_POSITION = "create table "
            + TABLE_POSITION + "(" + POSITION_POSITION
            + " text primary key, " + POSITION_SALARY
            + " integer not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_POSITION);
        database.execSQL(DATABASE_CREATE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void insertPosition(SQLiteDatabase db, String position, int salary){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", position);
        contentValues.put("salary", salary);
        db.insert("positions", null, contentValues);
    }

    public void insertPerson(SQLiteDatabase db, String name, String position){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("position", position);
        db.insert("persons", null, contentValues);
    }

    public ArrayList<Person> getPersons(SQLiteDatabase db, int salary){
        String table = "persons as PL inner join positions as PS on PL.position = PS.id";
        String columns[] = { "PL.name as Name", "PS.id as Position", "salary as Salary" };
        String selection = "salary < ?";
        String[] selectionArgs = {String.valueOf(salary)};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        return logCursor(c);
    }

    ArrayList<Person> logCursor(Cursor c) {
        ArrayList<Person> persons = new ArrayList<Person>();
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    Person p = new Person();
                    p.name = c.getString(0);
                    p.position = c.getString(1);
                    p.salary = Integer.valueOf(c.getString(2));

                    persons.add(p);
                } while (c.moveToNext());
            }
        }
        return persons;
    }

} 