package com.example.pokemonapp2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class wazaselect extends androidx.fragment.app.Fragment {
    Context d;
    View customAlertView;
    ArrayList<String> wazalist = new ArrayList<String>();
    ArrayList<String> wazalist2 = new ArrayList<String>();
    private FragmentActivity myContext;
    public wazaselect(ArrayList<String> wazali,ArrayList<String> wazali2){
        wazalist=wazali;
        wazalist2=wazali2;
    }


    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        customAlertView = inflater.inflate(R.layout.wazalayout, null);
        d= customAlertView.getContext();
        return inflater.inflate(R.layout.wazalayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //コンテキストはview

        TextView waza1 = view.findViewById(R.id.wazawaku1);
        waza1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new wazadailog(d,wazalist,1);
                dialogFragment.show(myContext.getSupportFragmentManager(), "my_dialog2");
            }
        });
        TextView waza2 = view.findViewById(R.id.wazawaku2);
        waza2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new wazadailog(d,wazalist,2);
                dialogFragment.show(myContext.getSupportFragmentManager(), "my_dialog2");
            }
        });
        TextView waza3 = view.findViewById(R.id.wazawaku3);
        waza3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new wazadailog(d,wazalist,3);
                dialogFragment.show(myContext.getSupportFragmentManager(), "my_dialog2");
            }
        });
        TextView waza4 = view.findViewById(R.id.wazawaku4);
        waza4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new wazadailog(d,wazalist,4);
                dialogFragment.show(myContext.getSupportFragmentManager(), "my_dialog2");
            }
        });

        if(wazalist2.get(0).equals("2")){

            waza1.setText(wazalist2.get(1));
            waza2.setText(wazalist2.get(2));
            waza3.setText(wazalist2.get(3));
            waza4.setText(wazalist2.get(4));

            
        }


    }


}
