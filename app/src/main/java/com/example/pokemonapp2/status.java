package com.example.pokemonapp2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class status extends androidx.fragment.app.Fragment {
    ArrayList<String> syuzokuti = new ArrayList<String>();
    ArrayList<String> kotaiti = new ArrayList<String>();
    ArrayList<String> doryokuti = new ArrayList<String>();
    TextView hp;
    TextView at;
    TextView df;
    TextView spat;
    TextView spdf;
    TextView spd;
    Spinner hpkotaiti;
    Spinner atkotaiti;
    Spinner dfkotaiti;
    Spinner spatkotaiti;
    Spinner spdfkotaiti;
    Spinner spdkotaiti;
    Spinner hpdoryokuti;
    Spinner atdoryokuti;
    Spinner dfdoryokuti;
    Spinner spatdoryokuti;
    Spinner spdfdoryokuti;
    Spinner spddoryokuti;
    Spinner seik;
    Context d;
    View customAlertView;
    double seat=1;
    double sedf=1;
    double sespat=1;
    double sespdf=1;
    double sespdd=1;
    double syu;
    double kotai;
    double doryoku;
    double level;
    double seikakuti;
    double nouryoku;
    public status(ArrayList<String> syuzokuli){
        syuzokuti=syuzokuli;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        customAlertView = inflater.inflate(R.layout.statuslayout, null);
        d= customAlertView.getContext();
        return inflater.inflate(R.layout.statuslayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int key = Integer.parseInt(syuzokuti.get(6));

        //種族値
        hp = view.findViewById(R.id.hpspinner0);
        hp.setText(syuzokuti.get(0));
        at = view.findViewById(R.id.atspinner0);
        at.setText(syuzokuti.get(1));
        df = view.findViewById(R.id.dfspinner0);
        df.setText(syuzokuti.get(2));
        spat = view.findViewById(R.id.spatspinner0);
        spat.setText(syuzokuti.get(3));
        spdf = view.findViewById(R.id.spdfspinner0);
        spdf.setText(syuzokuti.get(4));
        spd = view.findViewById(R.id.spdspinner0);
        spd.setText(syuzokuti.get(5));

        //性格値取得
        seik = view.findViewById(R.id.seikaku);

        int i;
        //個体値
        for (i = 0; i < 32; i++) {
            kotaiti.add(String.valueOf(i));
        }

        ArrayAdapter<String> kotaitiad = new ArrayAdapter<String>(d, android.R.layout.simple_spinner_item, kotaiti);
        hpkotaiti = view.findViewById(R.id.hpspinner1);
        hpkotaiti.setAdapter(kotaitiad);
        atkotaiti = view.findViewById(R.id.atspinner1);
        atkotaiti.setAdapter(kotaitiad);
        dfkotaiti = view.findViewById(R.id.dfspinner1);
        dfkotaiti.setAdapter(kotaitiad);
        spatkotaiti = view.findViewById(R.id.spatspinner1);
        spatkotaiti.setAdapter(kotaitiad);
        spdfkotaiti = view.findViewById(R.id.spdfspinner1);
        spdfkotaiti.setAdapter(kotaitiad);
        spdkotaiti = view.findViewById(R.id.spdspinner1);
        spdkotaiti.setAdapter(kotaitiad);


        //努力値
        for (i = 0; i < 255; i++) {
            doryokuti.add(String.valueOf(i));
        }
        ArrayAdapter<String> doryokutiad = new ArrayAdapter<String>(d, R.layout.spinner_item, doryokuti);
        hpdoryokuti = view.findViewById(R.id.hpspinner2);
        atdoryokuti = view.findViewById(R.id.atspinner2);
        dfdoryokuti = view.findViewById(R.id.dfspinner2);
        spatdoryokuti = view.findViewById(R.id.spatspinner2);
        spdfdoryokuti = view.findViewById(R.id.spdfspinner2);
        spddoryokuti = view.findViewById(R.id.spdspinner2);

        hpdoryokuti.setAdapter(doryokutiad);

        atdoryokuti.setAdapter(doryokutiad);

        dfdoryokuti.setAdapter(doryokutiad);

        spatdoryokuti.setAdapter(doryokutiad);

        spdfdoryokuti.setAdapter(doryokutiad);

        spddoryokuti.setAdapter(doryokutiad);
        //新規作成
        if (key == 0) {
            //個体値設定

            hpkotaiti.setSelection(31);

            atkotaiti.setSelection(30);

            dfkotaiti.setSelection(30);

            spatkotaiti.setSelection(30);

            spdfkotaiti.setSelection(30);

            spdkotaiti.setSelection(30);

            //努力値
            hpdoryokuti.setAdapter(doryokutiad);
            atdoryokuti.setAdapter(doryokutiad);
            dfdoryokuti.setAdapter(doryokutiad);
            spatdoryokuti.setAdapter(doryokutiad);
            spdfdoryokuti.setAdapter(doryokutiad);
            spddoryokuti.setAdapter(doryokutiad);
        } else {

            hpkotaiti.setSelection(Integer.parseInt(syuzokuti.get(7)));
            atkotaiti.setSelection(Integer.parseInt(syuzokuti.get(8)));
            dfkotaiti.setSelection(Integer.parseInt(syuzokuti.get(9)));
            spatkotaiti.setSelection(Integer.parseInt(syuzokuti.get(10)));
            spdfkotaiti.setSelection(Integer.parseInt(syuzokuti.get(11)));
            spdkotaiti.setSelection(Integer.parseInt(syuzokuti.get(12)));
            hpdoryokuti.setSelection(Integer.parseInt(syuzokuti.get(13)));
            atdoryokuti.setSelection(Integer.parseInt(syuzokuti.get(14)));
            dfdoryokuti.setSelection(Integer.parseInt(syuzokuti.get(15)));
            spatdoryokuti.setSelection(Integer.parseInt(syuzokuti.get(16)));
            spdfdoryokuti.setSelection(Integer.parseInt(syuzokuti.get(17)));
            spddoryokuti.setSelection(Integer.parseInt(syuzokuti.get(18)));


        }

    }
}
