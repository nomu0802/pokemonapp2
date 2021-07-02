package com.example.pokemonapp2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class status extends androidx.fragment.app.Fragment {
    ArrayList<String> syuzokuti = new ArrayList<String>();
    ArrayList<String> kotaiti = new ArrayList<String>();
    ArrayList<String> doryokuti = new ArrayList<String>();
    Context d;
    View customAlertView;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        int key = Integer.parseInt(syuzokuti.get(6));

        //種族値
        TextView hp = view.findViewById(R.id.hpspinner0);
        hp.setText(syuzokuti.get(0));
        TextView at = view.findViewById(R.id.atspinner0);
        at.setText(syuzokuti.get(1));
        TextView df = view.findViewById(R.id.dfspinner0);
        df.setText(syuzokuti.get(2));
        TextView spat = view.findViewById(R.id.spatspinner0);
        spat.setText(syuzokuti.get(3));
        TextView spdf = view.findViewById(R.id.spdfspinner0);
        spdf.setText(syuzokuti.get(4));
        TextView spd = view.findViewById(R.id.spdspinner0);
        spd.setText(syuzokuti.get(5));

        int i;
        //個体値
        for (i = 0; i < 32; i++) {
            kotaiti.add(String.valueOf(i));
        }

        ArrayAdapter<String> kotaitiad = new ArrayAdapter<String>(d, android.R.layout.simple_spinner_item, kotaiti);
        Spinner hpkotaiti = view.findViewById(R.id.hpspinner1);
        hpkotaiti.setAdapter(kotaitiad);
        Spinner atkotaiti = view.findViewById(R.id.atspinner1);
        atkotaiti.setAdapter(kotaitiad);
        Spinner dfkotaiti = view.findViewById(R.id.dfspinner1);
        dfkotaiti.setAdapter(kotaitiad);
        Spinner spatkotaiti = view.findViewById(R.id.spatspinner1);
        spatkotaiti.setAdapter(kotaitiad);
        Spinner spdfkotaiti = view.findViewById(R.id.spdfspinner1);
        spdfkotaiti.setAdapter(kotaitiad);
        Spinner spdkotaiti = view.findViewById(R.id.spdspinner1);
        spdkotaiti.setAdapter(kotaitiad);


        //努力値
        for (i = 0; i < 255; i++) {
            doryokuti.add(String.valueOf(i));
        }
        ArrayAdapter<String> doryokutiad = new ArrayAdapter<String>(d, R.layout.spinner_item, doryokuti);
        Spinner hpdoryokuti = view.findViewById(R.id.hpspinner2);
        Spinner atdoryokuti = view.findViewById(R.id.atspinner2);
        Spinner dfdoryokuti = view.findViewById(R.id.dfspinner2);
        Spinner spatdoryokuti = view.findViewById(R.id.spatspinner2);
        Spinner spdfdoryokuti = view.findViewById(R.id.spdfspinner2);
        Spinner spddoryokuti = view.findViewById(R.id.spdspinner2);

        hpdoryokuti.setAdapter(doryokutiad);

        atdoryokuti.setAdapter(doryokutiad);

        dfdoryokuti.setAdapter(doryokutiad);

        spatdoryokuti.setAdapter(doryokutiad);

        spdfdoryokuti.setAdapter(doryokutiad);

        spddoryokuti.setAdapter(doryokutiad);
        //新規作成
        if(key==0) {
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
        }
        else {

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

    void stats(){


    }



}
