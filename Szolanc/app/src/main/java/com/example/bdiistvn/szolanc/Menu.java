package com.example.bdiistvn.szolanc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {


    Button egyJattekos;
    SharedPreferences sharedPref;
    TextView tErmek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        egyJattekos = (Button) findViewById(R.id.egyjattekos);
        tErmek = (TextView) findViewById(R.id.tErmek);

        egyJattekos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent egyJattekosI = new Intent(getApplicationContext(),EgyJattekos.class);
                startActivity(egyJattekosI);

            }
        });


        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Log.d("Amit megkapsz", sharedPref.getInt("ermek", 0) + "");
        int ermek = sharedPref.getInt("ermek",0);
        tErmek.setText("Ermeid: " + ermek );


    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        int ermek = sharedPref.getInt("ermek",0);
        tErmek.setText("Ermeid: " + ermek);
        Log.d("Amit megkapsz", sharedPref.getInt("ermek", 0) + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        int ermek = sharedPref.getInt("ermek",0);
        tErmek.setText("Ermeid: " + ermek);
        Log.d("Amit megkapsz", sharedPref.getInt("ermek", 0) + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        int ermek = sharedPref.getInt("ermek",0);
        tErmek.setText("Ermeid: " + ermek);
        Log.d("Amit megkapsz", sharedPref.getInt("ermek", 0) + "");
    }
}


