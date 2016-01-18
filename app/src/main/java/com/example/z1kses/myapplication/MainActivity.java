package com.example.z1kses.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button bbr = (Button) findViewById(R.id.button);
        bbr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();

                    RelativeLayout lay= (RelativeLayout) findViewById(R.id.rLayout);
                    int width=lay.getWidth();
                    int height=lay.getHeight();

                    Random r = new Random();
                    Button bbl = (Button) findViewById(R.id.button);

                    height -= bbl.getHeight()+lay.getPaddingTop()+lay.getPaddingBottom();
                    width -= bbl.getWidth()+lay.getPaddingLeft()+lay.getPaddingRight();

                    int newX;
                    int newY;

                    int i=0;

                    do {
                        i++;

                        newX = r.nextInt(width)+lay.getPaddingLeft();
                        newY = r.nextInt(height)+lay.getPaddingTop();

                    }while ((x>newX&&x<(newX+bbl.getWidth()))||(y>newY&&y<(newY+bbl.getHeight())));

                    System.out.println(i);

                    bbl.setX(newX);
                    bbl.setY(newY);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
