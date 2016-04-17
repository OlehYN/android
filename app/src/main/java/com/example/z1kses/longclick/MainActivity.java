package com.example.z1kses.longclick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements
        OnTouchListener{
    private class UpdateCounterTask implements Runnable {
        private boolean mInc;

        public UpdateCounterTask(boolean inc) {
            mInc = inc;
        }

        public void run() {
                mHandler.sendEmptyMessage(MSG_INC);
        }
    }

    private static final int MSG_INC = 0;

    private Button mIncButton;
    private TextView mText;
    private int mCounter;

    private Handler mHandler;
    private ScheduledExecutorService mUpdater;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_INC:
                        inc();
                        return;
                }
                super.handleMessage(msg);
            }
        };
        mIncButton = (Button) findViewById(R.id.inc_button);
        mText = (TextView) findViewById(R.id.textfield);
        mIncButton.setOnTouchListener(this);
    }

    private void inc() {
        mCounter++;
        mText.setText(Integer.toString(mCounter));
        if(mCounter == 5)
            Toast.makeText(this, "Hello world!",
                    Toast.LENGTH_LONG).show();
    }

    private void startUpdating(boolean inc) {
        if (mUpdater != null) {
            Log.e(getClass().getSimpleName(), "Another executor is still active");
            return;
        }
        mUpdater = Executors.newSingleThreadScheduledExecutor();
        mUpdater.scheduleAtFixedRate(new UpdateCounterTask(inc), 1000, 1000,
                TimeUnit.MILLISECONDS);
    }

    private void stopUpdating() {
        mUpdater.shutdownNow();
        mUpdater = null;
    }

    public boolean onTouch(View v, MotionEvent event) {
        boolean isReleased = event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL;
        boolean isPressed = event.getAction() == MotionEvent.ACTION_DOWN;

        if (isReleased) {
            stopUpdating();
            mCounter = 0;
            mText.setText("Try again!");
        } else if (isPressed) {
            startUpdating(v == mIncButton);
        }
        return false;
    }
}