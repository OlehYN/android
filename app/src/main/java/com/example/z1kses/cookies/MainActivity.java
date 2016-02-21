package com.example.z1kses.cookies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Socket client;
    BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client cl = new Client();
        cl.execute();
    }

    private class Client extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("109.251.161.13", 4444); // connect to the server

                String message = "";
                in = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                while (!message.equals("печеньки")) {
                    message = in.readLine();
                }

                runOnUiThread(new Runnable() {
                    public void run() {

                        TextView v = (TextView) findViewById(R.id.textd);
                        v.setText("EVIL!");

                        Toast.makeText(MainActivity.this, "Ви перейшли на сторону зла!",
                                Toast.LENGTH_LONG).show();
                    }
                });


                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
