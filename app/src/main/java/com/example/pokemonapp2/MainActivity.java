package com.example.pokemonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bt(View view) {

        Intent intent = new Intent(getApplication(), pokesorch.class);
        startActivity(intent);
    }

    public void boxboutton(View view) {
        Intent intent3 = new Intent(getApplication(), pokegrid.class);
        startActivity(intent3);
    }
}