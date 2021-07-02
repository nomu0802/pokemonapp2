package com.example.pokemonapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ptDialogFragment extends DialogFragment {

    ArrayList<String> dougu = new ArrayList<>();
    ArrayList data = new ArrayList<>();
    ArrayList data2 = new ArrayList<>();
    ArrayList data3 = new ArrayList<>();
    Context d;
    public ptDialogFragment(Context c) {
        d=c;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        // カスタムレイアウトの用意
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View customAlertView = inflater.inflate(R.layout.ptcustom_dialog, null);

        // ダイアログの作成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customAlertView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // このボタンを押した時の処理を書きます。

                     }
                });
        return builder.create();
    }




}
