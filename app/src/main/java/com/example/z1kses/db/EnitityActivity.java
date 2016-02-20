package com.example.z1kses.db;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class EnitityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enitity);

        Intent intent = getIntent();

        int salary = 1000;

        try {
            salary = Integer.valueOf(intent.getStringExtra("name"));
        }catch(Exception e){

        }
        final ListView listview = (ListView) findViewById(R.id.listview);

        ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        ArrayList<Person> persons = dbh.getPersons(db, salary);

        HashMap<String, String> map;
        for(Person p:persons){
            map = new HashMap<String, String>();
            map.put("Name", p.name);
            map.put("Info", p.position+", "+p.salary);
            myArrList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, myArrList, android.R.layout.simple_list_item_2,
                new String[] {"Name", "Info"},
                new int[] {android.R.id.text1, android.R.id.text2});


        listview.setAdapter(adapter);
    }
}
