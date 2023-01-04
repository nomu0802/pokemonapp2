package com.example.pokemonapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class memo extends androidx.fragment.app.Fragment {
    ArrayList<String> memoli2 = new ArrayList<>();
    public memo(ArrayList<String> memoli){
        memoli2=memoli;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memolayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(memoli2.get(0)=="2") {

            EditText memo = view.findViewById(R.id.stwaku1);
            memo.setText(memoli2.get(1));
        }
    }
}
