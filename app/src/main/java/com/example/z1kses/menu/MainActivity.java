package com.example.z1kses.menu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Random r = new Random();
        TextView tf = (TextView) findViewById(R.id.msgTextField);
        switch (item.getItemId()) {
            case R.id.item1:
                tf.setTextColor(Color.rgb(r.nextInt(256), r.nextInt(256),
                        r.nextInt(256)));
                return true;
            case R.id.item2:
                String[] values = {"Пощадіть, будь ласка!", "Я невинен, мене підставили!", "Тільки не змінюйте розмір", "І це все?", "Не стидно?", "Хватить мене катувати!", "Зустрінемось у європейському суді!"};
                tf.setText(values[r.nextInt(values.length)]);
                return true;
            case R.id.item3:
                tf.setTextSize(r.nextInt(32));
                return true;
        }

        return true;
    }
}
