package com.example.kevinhuang.tomandjerry2;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AskSpike extends ActionBarActivity {
    private String url = "http://167.205.32.46/pbd/api/track?nim=13512096";
    private TextView latitude,longitude,valid,txtsecond;
    private HandleJSON obj;
    private JerryLocation locatejerry = new JerryLocation();
    private Button ShowMap,btnlocatejerry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_spike);
        latitude = (TextView)findViewById(R.id.txtView1);
        longitude = (TextView)findViewById(R.id.txtView2);
        valid = (TextView)findViewById(R.id.txtView3);
        txtsecond = (TextView)findViewById(R.id.txtsecond);
        ShowMap = (Button)findViewById(R.id.btnshowmap);
        ShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent catchintent = new Intent(view.getContext(),MapsActivity.class);
                startActivity(catchintent);
            }
        });
        btnlocatejerry = (Button)findViewById(R.id.btngetlocation);
        btnlocatejerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open(view);
            }
        });
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtsecond.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                txtsecond.setText("done!");
            }
        }.start();
    }

    public void open(View view){
        obj = new HandleJSON(url);
        obj.fetchJSON();

        while(obj.parsingComplete);
        latitude.setText(obj.getLatitude());
        longitude.setText(obj.getLongitude());
        valid.setText(obj.getValid());
        locatejerry.setLatitude(Double.parseDouble(obj.getLatitude()));
        locatejerry.setLongitude(Double.parseDouble(obj.getLongitude()));
    }
}

