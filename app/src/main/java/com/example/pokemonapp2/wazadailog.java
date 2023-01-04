package com.example.pokemonapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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

import java.util.ArrayList;

public class wazadailog extends DialogFragment {

    ArrayList data = new ArrayList<>();
    ArrayList data2 = new ArrayList<>();
    ArrayList data3 = new ArrayList<>();
    ArrayList data4 = new ArrayList<>();
    Context d;
    int idx;

    public wazadailog(Context c, ArrayList<String> wazalist, int id) {
        d = c;
        idx = id;
        data4.addAll(wazalist);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        // カスタムレイアウトの用意
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View customAlertView = inflater.inflate(R.layout.custom_waza_dialog, null);


        //道具読み込み
        json();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(d,android.R.layout.simple_list_item_1, data);
        ListView listView = customAlertView.findViewById(R.id.dougu);
        listView.setAdapter(adapter);


        // ダイアログの作成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customAlertView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // クリックされたアイテムを取得する。
                String item = (String) parent.getItemAtPosition(position);

                //道具の名前をセットする
                poketouroku callingActivity = (poketouroku) getActivity();
                callingActivity.wazaselect(item,idx);

                dismiss();
            }
        });

        listView.setTextFilterEnabled(true);
        EditText editText;
        editText = customAlertView.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.clear();
                adapter.addAll(data3);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String search = editText.getText().toString();
                data2.clear();
                // クリック時の処理
                for (int i=0;i<data3.size();i++) {
                    String str = (String) data3.get(i);
                    if (str.contains(search)) {
                        data2.add(str);
                    }
                }
                adapter.clear();
                adapter.addAll(data2);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.clear();
                data.addAll(data2);
            }
        });



        return builder.create();
    }
    void json(){
            data.addAll(data4);
            data3.addAll(data4);

    }
}