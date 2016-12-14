package com.example.bdiistvn.szolanc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class EgyJattekos extends AppCompatActivity {

    String[] a_szavak;
    String[] b_szavak;
    String[] cs_szavak;
    String[] c_szavak;
    String[] d_szavak;
    String[] e_szavak;
    String[] é_szavak;
    String[] f_szavak;
    String[] g_szavak;
    String[] h_szavak;
    String[] i_szavak;
    String[] j_szavak;
    String[] k_szavak;
    String[] l_szavak;
    String[] ly_szavak;
    String[] m_szavak;
    String[] n_szavak;
    String[] ny_szavak;
    String[] o_szavak;
    String[] ö_szavak;
    String[] ő_szavak;
    String[] p_szavak;
    String[] r_szavak;
    String[] s_szavak;
    String[] sz_szavak;
    String[] t_szavak;
    String[] ty_szavak;
    String[] u_szavak;
    String[] ü_szavak;
    String[] v_szavak;
    String[] w_szavak;
    String[] x_szavak;
    String[] z_szavak;
    String[] zs_szavak;



    ArrayList<String> eddigiek;

    EditText bevitel;
    Button bKuldes;
    Button bKihagy;
    Toast hiba;
    String kisorsolt;
    double pont;
    int ermek;
    TextView tErmek;
    TextView tPont;
    long ido = 20000;
    long mentettIdo;
    TextView hIdo;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyjattekos);
        final TextView textGepValasza = (TextView) findViewById(R.id.gepSzava);
        sharedPref= PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPref.edit();
        bevitel = (EditText) findViewById(R.id.bevittSzo);
        bKuldes = (Button) findViewById(R.id.kuldes);
        bKihagy = (Button) findViewById(R.id.bKihagy);
        hIdo = (TextView) findViewById(R.id.hIdo);
        tPont = (TextView) findViewById(R.id.tPont);
        tErmek= (TextView) findViewById(R.id.tErmek);
        final Context jelenlegi = this;
        eddigiek = new ArrayList<>();
        pont = 0;

        bKuldes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bevitel.getText().toString().equals("")) {
                    Log.e("Input hiba", "Nem adott meg semmit");
                    hiba.makeText(jelenlegi, "Nem adtál meg semmit", hiba.LENGTH_SHORT).show();


                } else {


                    //ha eddig nem volt egy szo se hozzá adja
                    if (eddigiek.size() == 0) {
                        Log.d("Sikeresen lefutott", "Sikerült leelenőriznie hogy üres-e");
                        if (letezik(bevitel.getText().toString())) {
                            Log.d("Sieresen futott", " Hozzáadta");

                            // hozzá adja a játtékos által beírt szót majd kisorsol egyet a gépnek is
                            // és azt is hozzáadja
                            eddigiek.add(bevitel.getText().toString());
                            gepValasza();
                            eddigiek.add(kisorsolt);
                            textGepValasza.setText(kisorsolt);
                            countDownTimer.cancel();
                            ido = 30000;
                            countDownTimer.start();
                            pont+=bevitel.getText().toString().length()*0.25;
                            tPont.setText("Pont: " + pont);
                            bevitel.setText("");


                        } else {
                            hiba.makeText(jelenlegi, "Nincs ilyen szó", hiba.LENGTH_SHORT).show();
                        }
                    } else {
                        //ellenőrizze hogy a szabálynak megfelően adott-e meg szót
                        if (ellenorzes(eddigiek.get(eddigiek.size() - 1), bevitel.getText().toString())) {

                            //ha létezik a szó akkor adja hozzá a eddigiek listához és a gépnek is
                            //sorsoljon ki egy szot amit szintén hozzáad
                            if (volt(bevitel.getText().toString())==false) {

                                eddigiek.add(bevitel.getText().toString());

                                gepValasza();
                                eddigiek.add(kisorsolt);
                                textGepValasza.setText(kisorsolt);
                                countDownTimer.cancel();
                                ido = 30000;

                                countDownTimer.start();
                                pont+=bevitel.getText().toString().length()*0.25;
                                tPont.setText("Pont : " + pont);
                                Log.d("Pontod:", pont + "");
                                bevitel.setText("");
                            }

                            // ha nincs ilyen szó írja ki
                            else {
                                Log.e("Hiba", "Nincs ilyen szó");
                                hiba.makeText(jelenlegi, "Nincs ilyen szó", hiba.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("Hiba", "Nem a szabálynak megfeleően adta meg a szót");
                            hiba.makeText(jelenlegi, "Nem a szabálynak megfelelően adtad meg", hiba.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        bKihagy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ermek>=50){
                    gepValasza();
                    eddigiek.add(kisorsolt);
                    textGepValasza.setText(kisorsolt);
                    ermek-=10;
                    editor.putInt("ermek", ermek);
                    editor.commit();
                    tErmek.setText("Ermek: " + ermek);
                }

            }
        });

        Log.d("Sikeres vágás", "Szétvágta az összes szót");
        a_szavak = getResources().getString(R.string.a).split(" ");
        b_szavak = getResources().getString(R.string.b).split(" ");
        cs_szavak= getResources().getString(R.string.cs).split(" ");
        c_szavak = getResources().getString(R.string.c).split(" ");
        d_szavak = getResources().getString(R.string.d).split(" ");
        e_szavak = getResources().getString(R.string.e).split(" ");
        é_szavak = getResources().getString(R.string.é).split(" ");
        f_szavak = getResources().getString(R.string.f).split(" ");
        g_szavak = getResources().getString(R.string.g).split(" ");
        h_szavak = getResources().getString(R.string.h).split(" ");
        i_szavak = getResources().getString(R.string.i).split(" ");
        j_szavak = getResources().getString(R.string.j).split(" ");
        k_szavak = getResources().getString(R.string.k).split(" ");
        l_szavak = getResources().getString(R.string.l).split(" ");
        ly_szavak = getResources().getString(R.string.ly).split(" ");
        m_szavak = getResources().getString(R.string.m).split(" ");
        n_szavak = getResources().getString(R.string.n).split(" ");
        ny_szavak= getResources().getString(R.string.ny).split(" ");
        o_szavak = getResources().getString(R.string.o).split(" ");
        ö_szavak = getResources().getString(R.string.ö).split(" ");
        ő_szavak = getResources().getString(R.string.ő).split(" ");
        p_szavak = getResources().getString(R.string.p).split(" ");
        r_szavak = getResources().getString(R.string.r).split(" ");
        s_szavak = getResources().getString(R.string.s).split(" ");
        sz_szavak=getResources().getString(R.string.sz).split(" ");
        t_szavak=getResources().getString(R.string.t).split(" ");
        ty_szavak=getResources().getString(R.string.ty).split(" ");
        u_szavak=getResources().getString(R.string.u).split(" ");
        ü_szavak=getResources().getString(R.string.ü).split(" ");
        v_szavak=getResources().getString(R.string.v).split(" ");
        w_szavak=getResources().getString(R.string.w).split(" ");
        x_szavak = getResources().getString(R.string.x).split(" ");
        z_szavak=getResources().getString(R.string.z).split(" ");
        zs_szavak=getResources().getString(R.string.zs).split(" ");


        ermek = sharedPref.getInt("ermek", 0);
        tErmek.setText("Ermek: " + ermek);
    }

    @Override
    protected void onStart() {
        super.onStart();
        countDownTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer = new CountDownTimer(mentettIdo,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                hIdo.setText(millisUntilFinished/1000+"");
                mentettIdo=millisUntilFinished;
            }

            @Override
            public void onFinish() {
                ermek = (int) pont;
                ermek+=sharedPref.getInt("ermek",0);
                editor.putInt("ermek",ermek);
                if(pont>sharedPref.getInt("rekord",0)){
                    editor.putInt("rekord",(int)pont);
                }
                editor.putInt("pont",(int)pont);
                editor.commit();
                Intent jattek = new Intent(getApplicationContext(),Menu.class);
                startActivity(jattek);

                finish();
            }
        }.start();


    }

    //ellenorzi hogy az elozo szonak az utolso betüjével kezdődik-e a mostani szó
    public boolean ellenorzes(String elozo, String mostani) {
        String utolso="";
        if(elozo.endsWith("ty") ||
                elozo.endsWith("sz") ||
                elozo.endsWith("cs") ||
                elozo.endsWith("ny") ||
                elozo.endsWith("ly") ||
                elozo.endsWith("zs") ||
                elozo.endsWith("gy")){
            utolso = elozo.substring(elozo.length()-2);
        }
        else{
            utolso = elozo.charAt(elozo.length() - 1)+"";
        }

        Log.d("kivagott szo",utolso);
        if (mostani.startsWith(utolso)) {
            return true;
        }
        return false;
    }


    public boolean volt(String szo) {
        if (letezik(szo)) {

                for (int i = 0; i < eddigiek.size(); i++) {
                    if (eddigiek.get(i).equals(szo)) {

                        Toast.makeText(this, "A szo már volt", Toast.LENGTH_SHORT).show();
                        return true;

                    }

                }
            return false;

            }
         else {

            Toast.makeText(this, "Nem letezik", Toast.LENGTH_SHORT).show();
            return true;
        }


    }

    public boolean letezik(String szo) {

        //a szo ellenorzese
        Log.d("Létezik-e:", szo);
        szo = szo.toLowerCase();
        if (szo.startsWith("a") || szo.startsWith("á")) {

            for (int i = 0; i < a_szavak.length; i++) {
                if (szo.equalsIgnoreCase(a_szavak[i])) {
                    return true;
                }


            }
        }

        //b szo ellenorzese
        else if (szo.startsWith("b")) {
            for (int i = 0; i < b_szavak.length; i++) {
                if (szo.equalsIgnoreCase(b_szavak[i])) {
                    return true;
                }
            }


        }

        //cs szo ellenorzese

        else if(szo.startsWith("cs")){
            for (int i = 0; i<cs_szavak.length;i++){
                if(szo.equalsIgnoreCase(cs_szavak[i])){
                    return true;
                }
            }
        }

        //c szo ellenorzese
        else if(szo.startsWith("c")){
            for (int i = 0; i<c_szavak.length;i++){
                if(szo.equalsIgnoreCase(c_szavak[i])){
                    return true;
                }
            }
        }

        //d szo ellenorzese
        else if(szo.startsWith("d")){
            for (int i = 0; i<d_szavak.length;i++){
                if(szo.equalsIgnoreCase(d_szavak[i])){
                    return true;
                }
            }
        }

        //e szo ellenorzese
        else if(szo.startsWith("e")){
            for (int i = 0; i<e_szavak.length;i++){
                if(szo.equalsIgnoreCase(e_szavak[i])){
                    return true;
                }
            }
        }

        //é szó ellenörzése

        else if(szo.startsWith("é")){
            for (int i = 0; i<é_szavak.length;i++){
                if(szo.equalsIgnoreCase(é_szavak[i])){
                    return true;
                }
            }
        }

        //f szo ellenörzése

        else if(szo.startsWith("f")){
            for (int i = 0; i<f_szavak.length; i++){
                if(szo.equalsIgnoreCase(f_szavak[i])){
                    return true;
                }
            }
        }

        //g szo ellenörzés

        else if(szo.startsWith("g") || szo.startsWith("gy")){
            for (int i = 0; i<g_szavak.length;i++){
                if(szo.equalsIgnoreCase(g_szavak[i])){
                    return true;
                }
            }
        }

        //h szo ellenorzese

        else if(szo.startsWith("h")){
            for (int i = 0; i<h_szavak.length;i++){
                if(szo.equalsIgnoreCase(h_szavak[i])){
                    return true;
                }
            }
        }

        // I Í szó ellenörzés
        else if(szo.startsWith("i") || szo.startsWith("í")){
            for (int i = 0; i<i_szavak.length; i++){
                if(szo.equalsIgnoreCase(i_szavak[i])){
                    return true;
                }
            }
        }

        // J ellenorzese
        else if(szo.startsWith("j")){
            for (int i = 0; i<j_szavak.length; i++){
                if(szo.equalsIgnoreCase(j_szavak[i])){
                    return true;
                }
            }
        }

        //K ellenorzese
        else if(szo.startsWith("k")){
            for (int i = 0; i<k_szavak.length; i++){
                if(szo.equalsIgnoreCase(k_szavak[i])){
                    return true;
                }
            }
        }

        //LY szavak

        else if(szo.startsWith("ly")){
            for (int i = 0; i<ly_szavak.length; i++){
                if(szo.equalsIgnoreCase(ly_szavak[i])){
                    return true;
                }
            }
        }

        //L szavak
        else if(szo.startsWith("l")){
            for (int i = 0; i<l_szavak.length; i++){
                if(szo.equalsIgnoreCase(l_szavak[i])){
                    return true;
                }
            }
        }

        //M szavak
        else if(szo.startsWith("m")){
            for (int i = 0; i<m_szavak.length; i++){
                if(szo.equalsIgnoreCase(m_szavak[i])){
                    return true;
                }
            }
        }

        //Ny szavak
        else if(szo.startsWith("ny")){
            for (int i = 0; i<ny_szavak.length;i++){
                if(szo.equalsIgnoreCase(ny_szavak[i])){
                    return true;
                }
            }
        }

        //N szavak

        else if(szo.startsWith("n")){
            for (int i = 0; i<n_szavak.length;i++){
                if(szo.equalsIgnoreCase(n_szavak[i])){
                    return true;
                }
            }
        }

        //O szavak

        else if(szo.startsWith("o") || szo.startsWith("ó")){
            for (int i = 0; i<o_szavak.length;i++){
                if(szo.equalsIgnoreCase(o_szavak[i])){
                    return true;
                }
            }
        }

        //Ö szavak

        else if(szo.startsWith("ö")){
            for (int i = 0; i<ö_szavak.length;i++){
                if(szo.equalsIgnoreCase(ö_szavak[i])){
                    return true;
                }
            }
        }

        //Ő szavak
        else if(szo.startsWith("ő")){
            for (int i = 0; i<ő_szavak.length;i++){
                if(szo.equalsIgnoreCase(ő_szavak[i])){
                    return true;
                }
            }
        }

        //P szavak

        else if(szo.startsWith("p")){
            for (int i = 0; i<p_szavak.length;i++){
                if(szo.equalsIgnoreCase(p_szavak[i])){
                    return true;
                }
            }
        }

        //R szavak

        else if(szo.startsWith("r")){
            for (int i = 0; i<r_szavak.length;i++){
                if(szo.equalsIgnoreCase(r_szavak[i])){
                    return true;
                }
            }
        }

        //Sz szavak

        else if(szo.startsWith("sz")){
            for (int i = 0; i<sz_szavak.length; i++){
                if(szo.equalsIgnoreCase(sz_szavak[i])){
                    return true;
                }
            }
        }

        //S szavak

        else if(szo.startsWith("s")){
            for (int i = 0; i<s_szavak.length; i++){
                if(szo.equalsIgnoreCase(s_szavak[i])){
                    return true;
                }
            }
        }

        //Ty szavak

        else if(szo.startsWith("ty")){
            for (int i = 0; i<ty_szavak.length;i++){
                if(szo.equalsIgnoreCase(ty_szavak[i])){
                    return true;
                }
            }
        }

        //T szavak

        else if(szo.startsWith("t")){
            for (int i = 0; i<t_szavak.length;i++){
                if(szo.equalsIgnoreCase(t_szavak[i])){
                    return true;
                }
            }
        }

        //U szavak

        else if(szo.startsWith("u") || szo.startsWith("ú")){
            for (int i =0; i<u_szavak.length;i++){
                if(szo.equalsIgnoreCase(u_szavak[i])){
                    return true;
                }
            }
        }

        //Ü szavak

        else if(szo.startsWith("ü") || szo.startsWith("ű")){
            for (int i = 0; i<ü_szavak.length;i++){
                if(szo.equalsIgnoreCase(ü_szavak[i])){
                    return true;
                }
            }
        }

        //V szavak

        else if(szo.startsWith("v")){
            for (int i = 0; i<v_szavak.length;i++){
                if(szo.equalsIgnoreCase(v_szavak[i])){
                    return true;
                }
            }
        }

        //W szo

        else if(szo.startsWith("w")){
            for (int i = 0; i<w_szavak.length;i++){
                if(szo.equalsIgnoreCase(w_szavak[i])){
                    return true;
                }
            }
        }

        //X szavak

        else if(szo.startsWith("x")){
            for (int i = 0; i<x_szavak.length;i++){
                if(szo.equalsIgnoreCase(x_szavak[i])){
                    return true;
                }
            }
        }

        //Zs szavak
        else if(szo.startsWith("zs")){
            for (int i = 0; i<zs_szavak.length;i++){
                if(szo.equalsIgnoreCase(zs_szavak[i])){
                    return true;
                }
            }
        }

        //Z szavak

        else if(szo.startsWith("z")){
            for (int i = 0; i<z_szavak.length;i++){
                if(szo.equalsIgnoreCase(z_szavak[i])){
                    return true;
                }
            }
        }





        Log.d("Hiba", "Nem letezik");
        return false;



    }



    public void gepValasza(){


        //ha eddig nem volt szó akkor a gép kezd
        if(eddigiek.size()==0){
            Random r = new Random();
            int vel = r.nextInt(34)+1;

            switch(vel){
                case 1 : kisorsolt = a_szavak[veletlen(a_szavak)];
                    break;

                case 2 : kisorsolt = b_szavak[veletlen(b_szavak)];
                    break;

                case 3 : kisorsolt = c_szavak[veletlen(c_szavak)];
                    break;

                case 4 : kisorsolt = d_szavak[veletlen(d_szavak)];
                    break;

                case 5 : kisorsolt = e_szavak[veletlen(e_szavak)];
                    break;

                case 6 : kisorsolt = é_szavak[veletlen(é_szavak)];
                    break;

                case 7 : kisorsolt = f_szavak[veletlen(f_szavak)];
                    break;

                case 8 : kisorsolt = g_szavak[veletlen(g_szavak)];
                    break;

                case 9 : kisorsolt = h_szavak[veletlen(h_szavak)];
                    break;

                case 10 : kisorsolt= i_szavak[veletlen(i_szavak)];
                    break;

                case 11 : kisorsolt=cs_szavak[veletlen(cs_szavak)];
                    break;

                case 12 : kisorsolt=j_szavak[veletlen(j_szavak)];
                    break;

                case 13 : kisorsolt=k_szavak[veletlen(k_szavak)];
                    break;

                case 14 : kisorsolt=l_szavak[veletlen(l_szavak)];
                    break;

                case 15 : kisorsolt=ly_szavak[veletlen(ly_szavak)];
                    break;

                case 16 : kisorsolt=m_szavak[veletlen(m_szavak)];
                    break;

                case 17 : kisorsolt=n_szavak[veletlen(n_szavak)];
                    break;

                case 18 : kisorsolt= ny_szavak[veletlen(ny_szavak)];
                    break;

                case 19 : kisorsolt= o_szavak[veletlen(o_szavak)];
                    break;

                case 20 : kisorsolt = ö_szavak[veletlen(ö_szavak)];
                    break;

                case 21 : kisorsolt = ő_szavak[veletlen(ő_szavak)];
                    break;

                case 22 : kisorsolt = p_szavak[veletlen(p_szavak)];
                    break;

                case 23 : kisorsolt = r_szavak[veletlen(r_szavak)];
                    break;

                case 24 : kisorsolt = s_szavak[veletlen(s_szavak)];
                    break;

                case 25 : kisorsolt = sz_szavak[veletlen(sz_szavak)];
                    break;

                case 26 : kisorsolt = t_szavak[veletlen(t_szavak)];
                    break;

                case 27 : kisorsolt = ty_szavak[veletlen(ty_szavak)];
                    break;

                case 28 : kisorsolt = u_szavak[veletlen(u_szavak)];
                    break;

                case 29 : kisorsolt = ü_szavak[veletlen(ü_szavak)];
                    break;

                case 30 : kisorsolt = v_szavak[veletlen(v_szavak)];
                    break;

                case 31 : kisorsolt = w_szavak[veletlen(w_szavak)];
                    break;

                case 32 : kisorsolt= x_szavak[veletlen(x_szavak)];
                    break;

                case 33 : kisorsolt=z_szavak[veletlen(z_szavak)];
                    break;

                case 34 : kisorsolt=zs_szavak[veletlen(zs_szavak)];
                    break;
            }
        }



        else{

            //kisorsol a szabálynak megfelelő betüt
            String utolso;
            if(eddigiek.get(eddigiek.size()-1).endsWith("ty")
                    || eddigiek.get(eddigiek.size()-1).endsWith("sz")
                    || eddigiek.get(eddigiek.size()-1).endsWith("cs")
                    || eddigiek.get(eddigiek.size()-1).endsWith("ny")
                    || eddigiek.get(eddigiek.size()-1).endsWith("ly")
                    || eddigiek.get(eddigiek.size()-1).endsWith("zs")
                    || eddigiek.get(eddigiek.size()-1).endsWith("gy")){
                utolso = eddigiek.get(eddigiek.size()-1).substring(eddigiek.get(eddigiek.size()-1).length()-2);
                Log.d("A kivágott:", utolso);
            }
            else {
                utolso = eddigiek.get(eddigiek.size() - 1).charAt(eddigiek.get(eddigiek.size() - 1).length() - 1) + "";
            }

            utolso = utolso.toLowerCase();
            switch(utolso){
                case "a":  kisorsolt=ekezetSors(utolso);
                    break;

                case "á": kisorsolt=ekezetSors(utolso);
                    break;

                case "b": kisorsolt=b_szavak[veletlen(b_szavak)];
                    break;

                case "cs":kisorsolt=cs_szavak[veletlen(cs_szavak)];
                    break;

                case "c": kisorsolt=c_szavak[veletlen(c_szavak)];
                    break;

                case "d": kisorsolt=d_szavak[veletlen(d_szavak)];
                    break;

                case "e": kisorsolt=e_szavak[veletlen(e_szavak)];
                    break;

                case "é": kisorsolt=é_szavak[veletlen(é_szavak)];
                    break;

                case "f": kisorsolt=f_szavak[veletlen(f_szavak)];
                    break;

                case "g": kisorsolt=ekezetSors(utolso);
                    break;

                case "gy": kisorsolt=ekezetSors(utolso);
                    break;

                case "h": kisorsolt=h_szavak[veletlen(h_szavak)];
                    break;

                case "i" : kisorsolt=ekezetSors(utolso);
                    break;

                case "í": kisorsolt=ekezetSors(utolso);
                    break;

                case "j" : kisorsolt=j_szavak[veletlen(j_szavak)];
                    break;

                case "k" : kisorsolt=k_szavak[veletlen(k_szavak)];
                    break;

                case "ly": kisorsolt = ly_szavak[veletlen(ly_szavak)];
                    break;

                case "l" : kisorsolt=l_szavak[veletlen(l_szavak)];
                    break;

                case "m" : kisorsolt=m_szavak[veletlen(m_szavak)];
                    break;

                case "ny" : kisorsolt=ny_szavak[veletlen(ny_szavak)];
                    break;

                case "n" : kisorsolt=n_szavak[veletlen(n_szavak)];
                    break;

                case "o" :  kisorsolt=ekezetSors(utolso);
                    break;

                case "ó" : kisorsolt=ekezetSors(utolso);
                    break;

                case "ö" : kisorsolt=ö_szavak[veletlen(ö_szavak)];
                    break;

                case "ő" : kisorsolt=ő_szavak[veletlen(ő_szavak)];
                    break;

                case "p" : kisorsolt=p_szavak[veletlen(p_szavak)];
                    break;

                case "r" : kisorsolt=r_szavak[veletlen(r_szavak)];
                    break;

                case "s" : kisorsolt=s_szavak[veletlen(s_szavak)];
                    break;

                case "sz" : kisorsolt=sz_szavak[veletlen(sz_szavak)];
                    break;

                case "t" : kisorsolt=t_szavak[veletlen(t_szavak)];
                    break;

                case "ty": kisorsolt=ty_szavak[veletlen(ty_szavak)];
                    break;

                case "u" : kisorsolt=ekezetSors(utolso);
                    break;

                case "ú" : kisorsolt=ekezetSors(utolso);
                    break;

                case "ü" : kisorsolt=ekezetSors(utolso);
                    break;

                case "ű" : kisorsolt=ekezetSors(utolso);
                    break;

                case "v" : kisorsolt=v_szavak[veletlen(v_szavak)];
                    break;

                case "w" : kisorsolt=w_szavak[veletlen(w_szavak)];
                    break;

                case "x" : kisorsolt=x_szavak[veletlen(x_szavak)];
                    break;

                case "z" : kisorsolt=z_szavak[veletlen(z_szavak)];
                    break;

                case "zs" : kisorsolt=zs_szavak[veletlen(zs_szavak)];
                    break;






            }
        }

    }

    public String ekezetSors(String szo){


        if(szo.startsWith("á")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<a_szavak.length;i++){
                if(a_szavak[i].startsWith("á")){
                    kisors.add(a_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("a")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<a_szavak.length;i++){
                if(a_szavak[i].startsWith("a")){
                    kisors.add(a_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }



        else if(szo.startsWith("í")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i = 0; i<i_szavak.length;i++){
                if(i_szavak[i].startsWith("í")){
                    kisors.add(i_szavak[i]);
                }
            }
            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));
        }

       else if(szo.startsWith("i")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<i_szavak.length;i++){
                if(i_szavak[i].startsWith("i")){
                    kisors.add(i_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("ó")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i = 0; i<o_szavak.length;i++){
                if(o_szavak[i].startsWith("ó")){
                    kisors.add(o_szavak[i]);
                }
            }
            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));
        }

        else if(szo.startsWith("o")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<o_szavak.length;i++){
                if(o_szavak[i].startsWith("o")){
                    kisors.add(o_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("ú")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i = 0; i<u_szavak.length;i++){
                if(u_szavak[i].startsWith("ú")){
                    kisors.add(u_szavak[i]);
                }
            }
            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));
        }

        else if(szo.startsWith("u")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<u_szavak.length;i++){
                if(u_szavak[i].startsWith("u")){
                    kisors.add(u_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("ü")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<ü_szavak.length;i++){
                if(ü_szavak[i].startsWith("ü")){
                    kisors.add(ü_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("ű")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<ü_szavak.length;i++){
                if(ü_szavak[i].startsWith("ű")){
                    kisors.add(ü_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }

        else if(szo.startsWith("gy")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<g_szavak.length;i++){
                if(g_szavak[i].startsWith("gy")){
                    kisors.add(g_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }


        else if(szo.startsWith("g")){
            ArrayList<String> kisors = new ArrayList<>();
            for (int i =0; i<g_szavak.length;i++){
                if(g_szavak[i].startsWith("g")){
                    kisors.add(g_szavak[i]);
                }
            }


            return kisors.get(veletlen(kisors.toArray(new String[kisors.size()])));

        }




        return "hiba";
    }


    //visszaad egy véletlen számot
    public int veletlen(String [] szavak){
        Random r = new Random();
        int sors;
        do{
            sors =r.nextInt(szavak.length);
        }
        while(volt(szavak[sors]));
        return sors;

    }

    CountDownTimer countDownTimer = new CountDownTimer(ido,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            hIdo.setText(millisUntilFinished/1000+"");
            mentettIdo = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            ermek = (int)pont;
            ermek+=sharedPref.getInt("ermek",0);
            editor.putInt("ermek",ermek);
            if(pont>sharedPref.getInt("rekord",0)){
                editor.putInt("rekord",(int)pont);
            }
            editor.putInt("pont",(int)pont);
            Intent jattek = new Intent(getApplicationContext(),Menu.class);
            startActivity(jattek);
            editor.commit();
            finish();

        }
    };
}
