package com.example.bdiistvn.szolanc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JattekVege extends AppCompatActivity {


    TextView rekord;
    TextView elert;
    Button menu;
    Button ujJattek;
    SharedPreferences sharedPrefer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jattek_vege);
        rekord = (TextView)findViewById(R.id.rekord);
        elert = (TextView)findViewById(R.id.elert);
        menu = (Button)findViewById(R.id.menu);
        ujJattek=(Button)findViewById(R.id.ujJattek);
        sharedPrefer = PreferenceManager.getDefaultSharedPreferences(this);
        rekord.setText(sharedPrefer.getInt("rekord",0));
        elert.setText(sharedPrefer.getInt("pont",0));


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ujJattek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jattek = new Intent(getApplicationContext(),EgyJattekos.class);
                startActivity(jattek);
                finish();
            }
        });



    }
}
